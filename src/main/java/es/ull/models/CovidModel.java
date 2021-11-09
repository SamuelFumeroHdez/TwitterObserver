package es.ull.models;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CovidModel {

    public ArrayList<ArrayList<String>> arrayDatosEjes = new ArrayList<>();
    private String country;

    public CovidModel(String country){
        this.country = country;
        agruparDatosEjes(this.country);
    }

    private void agruparDatosEjes(String country){

        this.arrayDatosEjes.clear();

        StringBuilder result = new StringBuilder();
        URL url;
        ArrayList<String> datosEjeX = new ArrayList<>();
        ArrayList<String> datosEjeY = new ArrayList<>();
        String urlString = "https://api.covid19tracking.narrativa.com/api/2021-02-02";
        if(country.equalsIgnoreCase("World")){

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
                LinkedTreeMap<String,Object> totalHash = (LinkedTreeMap<String, Object>) resMap.get("total");

                datosEjeY.add(String.valueOf(totalHash.get("today_confirmed")));
                System.out.println(String.valueOf(totalHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(totalHash.get("today_deaths")));
                datosEjeY.add(String.valueOf(totalHash.get("today_recovered")));
                datosEjeY.add(String.valueOf(totalHash.get("today_new_confirmed")));
                datosEjeY.add(String.valueOf(totalHash.get("today_new_deaths")));
                datosEjeY.add(String.valueOf(totalHash.get("today_new_recovered")));

                datosEjeX.add("Casos");
                datosEjeX.add("Muertos");
                datosEjeX.add("Recuperados");
                datosEjeX.add("Nuevos casos");
                datosEjeX.add("Nuevas muertes");
                datosEjeX.add("Nuevos recuperados");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(country.equalsIgnoreCase("Compare")){

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

                LinkedTreeMap<String,Object> datesHash = (LinkedTreeMap<String, Object>) resMap.get("dates");
                LinkedTreeMap<String,Object> yesterdayHash = (LinkedTreeMap<String, Object>) datesHash.get("2021-02-02");
                LinkedTreeMap<String,Object> countriesHash = (LinkedTreeMap<String, Object>) yesterdayHash.get("countries");
                LinkedTreeMap<String,Object> usHash = (LinkedTreeMap<String, Object>) countriesHash.get("US");
                LinkedTreeMap<String,Object> spainHash = (LinkedTreeMap<String, Object>) countriesHash.get("Spain");
                LinkedTreeMap<String,Object> ukHash = (LinkedTreeMap<String, Object>) countriesHash.get("United Kingdom");
                LinkedTreeMap<String,Object> brazilHash = (LinkedTreeMap<String, Object>) countriesHash.get("Brazil");
                LinkedTreeMap<String,Object> indiaHash = (LinkedTreeMap<String, Object>) countriesHash.get("India");
                LinkedTreeMap<String,Object> russiaHash = (LinkedTreeMap<String, Object>) countriesHash.get("Russia");

                datosEjeY.add(String.valueOf(usHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(spainHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(ukHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(brazilHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(indiaHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(russiaHash.get("today_confirmed")));


                datosEjeX.add("EEUU");
                datosEjeX.add("España");
                datosEjeX.add("Reino Unido");
                datosEjeX.add("Brasil");
                datosEjeX.add("India");
                datosEjeX.add("Rusia");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

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

                LinkedTreeMap<String,Object> datesHash = (LinkedTreeMap<String, Object>) resMap.get("dates");
                LinkedTreeMap<String,Object> yesterdayHash = (LinkedTreeMap<String, Object>) datesHash.get("2021-02-02");
                LinkedTreeMap<String,Object> countriesHash = (LinkedTreeMap<String, Object>) yesterdayHash.get("countries");
                LinkedTreeMap<String,Object> countryHash = (LinkedTreeMap<String, Object>) countriesHash.get(country);

                datosEjeY.add(String.valueOf(countryHash.get("today_confirmed")));
                datosEjeY.add(String.valueOf(countryHash.get("today_deaths")));
                datosEjeY.add(String.valueOf(countryHash.get("today_recovered")));
                datosEjeY.add(String.valueOf(countryHash.get("today_new_confirmed")));
                datosEjeY.add(String.valueOf(countryHash.get("today_new_deaths")));
                datosEjeY.add(String.valueOf(countryHash.get("today_new_recovered")));

                datosEjeX.add("Casos");
                datosEjeX.add("Fallecidos");
                datosEjeX.add("Recuperados");
                datosEjeX.add("Nuevos casos");
                datosEjeX.add("Nuevas defunciones");
                datosEjeX.add("Nuevos recuperados");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        arrayDatosEjes.add(datosEjeX);
        arrayDatosEjes.add(datosEjeY);
        System.out.println(arrayDatosEjes);
    }

    private static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>() {}.getType()
        );
        return map;
    }
}
