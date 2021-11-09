package es.ull.views;

import es.ull.menus.CovidMenu;
import es.ull.menus.TwitterMenu;

import javax.swing.*;

public class VistaCovid extends JDesktopPane {

    private CovidMenu covidMenu;

    public VistaCovid(){
        covidMenu = new CovidMenu(this);
        add(covidMenu);
        setVisible(true);
    }

}
