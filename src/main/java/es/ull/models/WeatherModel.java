package es.ull.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherModel implements Runnable{

    public ArrayList<ArrayList<String>> arrayDatosEjes = new ArrayList<>();
    private final String apiKey = "05003dea9959651b65d2e3cc7f275b59";
    private String location;

    public WeatherModel(String location){
        this.location = location;

    }

    private void agruparDatosEjes(){
        this.arrayDatosEjes.clear();
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="+this.location+"&appid="+this.apiKey;
        StringBuilder result = new StringBuilder();
        URL url = null;
        ArrayList<String> datosEjeX = new ArrayList<>();
        ArrayList<String> datosEjeY = new ArrayList<>();

        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=rd.readLine()) != null){
                result.append(line);
            }
            rd.close();

            Map<String,Object> resMap = jsonToMap(result.toString());
            Map<String,Object> mainMap = jsonToMap(resMap.get("main").toString());
            Map<String,Object> windMap = jsonToMap(resMap.get("wind").toString());

            datosEjeY.add("Temp");
            datosEjeY.add("Temp max");
            datosEjeY.add("Temp min");
            datosEjeY.add("Humedad");
            datosEjeY.add("Viento");

            datosEjeX.add( String.valueOf(mainMap.get("temp")));
            datosEjeX.add(String.valueOf(mainMap.get("temp_max")));
            datosEjeX.add(String.valueOf(mainMap.get("temp_min")));
            datosEjeX.add(String.valueOf(mainMap.get("humidity")));
            datosEjeX.add(String.valueOf(windMap.get("speed")));

            arrayDatosEjes.add(datosEjeX);
            arrayDatosEjes.add(datosEjeY);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>() {}.getType()
        );
        return map;
    }

    @Override
    public void run() {
        agruparDatosEjes();
        for(;;){
            try {
                Thread.sleep(10000);
                agruparDatosEjes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
