package es.ull.views;

import es.ull.menus.TwitterMenu;
import es.ull.menus.WeatherMenu;

import javax.swing.*;

public class VistaWeather extends JDesktopPane {
    private WeatherMenu weatherMenu;

    public VistaWeather(){

        weatherMenu = new WeatherMenu(this);
        add(weatherMenu);
        setVisible(true);
    }

}
