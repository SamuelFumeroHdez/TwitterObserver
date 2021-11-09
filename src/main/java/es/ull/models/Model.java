package es.ull.models;

import es.ull.twitter.TwitterConector;
import twitter4j.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Model implements Runnable {
    Twitter twitter;
    private int woeid;
    ArrayList<Trend> trendList = new ArrayList<>();
    ArrayList<Trend> oldTrendList;
    public ArrayList<ArrayList<String>> arrayDatosEjes = new ArrayList<>();
    private static int timeSleep = 10000;
    HashMap<String, Integer> trendVolumeList = new HashMap<>();


    public Model(int woeid) {
        this.woeid = woeid;
        this.twitter = new TwitterConector().getTwitter();
    }

    public int getTrendVolume(String trendName) {
        Trends trends = null;
        try {
            trends = twitter.getPlaceTrends(woeid);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (Trend trend : trends.getTrends()) {
            if (trend.getName().equalsIgnoreCase(trendName)) {
                return trend.getTweetVolume();
            }
        }
        return 0;
    }

    public HashMap<String, Integer> getTrendsNamesAndVolumes() {
        try {
            saveTrends();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> list = new HashMap<>();
        trendList.forEach((trend) -> list.put(trend.getName(), trend.getTweetVolume()));
        return list;
    }

    private void saveTrends() throws TwitterException {
        Trends trends = twitter.getPlaceTrends(woeid);
        this.trendList.clear();
        for (Trend trend : trends.getTrends()) {
            if (trend.getTweetVolume() != -1) {
                trendList.add(trend);
            }

        }
    }

    private void saveTrendsVolume() {
        for (Trend trend : trendList) {
            trendVolumeList.put(trend.getName(), trend.getTweetVolume());
        }
    }

    public ArrayList<Trend> getTrendList() {
        try {
            saveTrends();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return this.trendList;
    }

    public ArrayList<Trend> getOldTrendList() {
        return this.oldTrendList;
    }

    public void agruparDatosEjes() {

        try {
            saveTrends();
            ArrayList<ArrayList<String>> arrayTrends = new ArrayList<>();
            ArrayList<String> arrayDatosEjeX = new ArrayList<>();
            ArrayList<String> arrayDatosEjeY = new ArrayList<>();
            for (Trend trend : trendList) {
                arrayDatosEjeX.add(String.valueOf(trend.getTweetVolume()));
                arrayDatosEjeY.add(trend.getName());
            }
            arrayTrends.add(arrayDatosEjeX);
            arrayTrends.add(arrayDatosEjeY);
            this.arrayDatosEjes = arrayTrends;
        } catch (TwitterException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void run() {
        agruparDatosEjes();
        for (; ; ) {
            this.oldTrendList = this.trendList;
            System.out.println(oldTrendList);
            try {
                Thread.sleep(timeSleep);
                agruparDatosEjes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Empezamos a guardar tweets");

            /*saveTrends();
            saveTrendsVolume();*/

        }
    }
}
