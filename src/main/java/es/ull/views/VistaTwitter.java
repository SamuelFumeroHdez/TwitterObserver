package es.ull.views;

import es.ull.menus.TwitterMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaTwitter extends JDesktopPane {
    private TwitterMenu menuTwitter;

    public VistaTwitter(){

        menuTwitter = new TwitterMenu(this);
        add(menuTwitter);
        setVisible(true);
    }

}


