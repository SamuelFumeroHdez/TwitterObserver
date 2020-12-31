import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.TreeMap;

public class Model implements Runnable {
    Twitter twitter;
    private int woeid;
    ArrayList<Trend> trendList = new ArrayList<>();
    ArrayList<Trend> oldTrendList;
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

    public ArrayList<Trend> getTrendList(){
        try {
            saveTrends();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return this.trendList;
    }

    public ArrayList<Trend> getOldTrendList(){
        return this.oldTrendList;
    }

    @Override
    public void run() {
        try {
            saveTrends();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for(;;){
            this.oldTrendList = this.trendList;
            try {
                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Empezamos a guardar tweets");
                saveTrends();
                saveTrendsVolume();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

    }
}
