import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.TreeMap;

public class Model extends TimerTask {
    Twitter twitter;
    private int woeid;
    ArrayList<Trend> trendList = new ArrayList<>();
    private static int timeSleep = 10000;
    HashMap<String,Integer> trendVolumeList = new HashMap<>();

    Model(int woeid){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDaemonEnabled(true)
                .setOAuthConsumerKey("iRqa9hqC8ydRw0dk5kkgqZjCW")
                .setOAuthConsumerSecret("HWLiI5T35IiU5X5wtA1IxM8UnO02e02SJODzgOpL61dW6sTi6C")
                .setOAuthAccessToken("1136742048080568330-nE9bgDeHXARMO34O5BwGh6nAXpgog0")
                .setOAuthAccessTokenSecret("gkooBOJL6bgE0ZSVmrSW2pE6geYfGOC78p046UbLNo9ru");

        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
        this.woeid = woeid;
    }

    public int getTrendVolume(String trendName){
        Trends trends = null;
        try {
            trends = twitter.getPlaceTrends(woeid);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for(Trend trend : trends.getTrends()) {
            if (trend.getName().equalsIgnoreCase(trendName)) {
                return trend.getTweetVolume();
            }
        }
        return 0;
    }

    public HashMap<String,Integer> getTrendsNamesAndVolumes(){
        try {
            saveTrends();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> list = new HashMap<>();
        trendList.forEach((trend) -> list.put(trend.getName(),trend.getTweetVolume()));
        return list;
    }

    private void saveTrends() throws TwitterException {
        Trends trends = twitter.getPlaceTrends(woeid);
        this.trendList.clear();
        for(Trend trend : trends.getTrends()) {
            if(trend.getTweetVolume()!=-1){
                trendList.add(trend);
            }

        }
    }

    private void saveTrendsVolume(){
        for (Trend trend : trendList){
            trendVolumeList.put(trend.getName(),trend.getTweetVolume());
        }
    }

    @Override
    public void run() {
        boolean change;
        int cont;
        for(;;){
            ArrayList<Trend> oldTrendList = this.trendList;
            HashMap<String,Integer> oldTrendVolumeList = this.trendVolumeList;

            try {
                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                saveTrends();
                saveTrendsVolume();
                change = false;
                cont = 0;
                while(!change && cont<this.trendList.size()){
                    if(this.trendList.get(cont).getName().equalsIgnoreCase(oldTrendList.get(cont).getName()) ||
                            this.trendList.get(cont).getTweetVolume() != oldTrendList.get(cont).getTweetVolume()){

                        change = true;
                    }
                }
                if (change){
                    System.out.println("Nueva lista -> " + this.trendList);
                }else{
                    System.out.println("Lista actual -> " + oldTrendList);
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

    }
}
