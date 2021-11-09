package es.ull;

import es.ull.views.VistaCovid;
import es.ull.views.VistaTwitter;
import es.ull.views.VistaWeather;

import javax.swing.*;

public class VentanaPrincipal extends JFrame{
    private VistaCovid vistaCovid;
    private VistaTwitter vistaTwitter;
    private VistaWeather vistaWeather;
    public VentanaPrincipal(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("Cuadro de Mandos");

        JTabbedPane vistas = new JTabbedPane();

        vistaTwitter = new VistaTwitter();
        vistas.add("Twitter", vistaTwitter);

        vistaCovid = new VistaCovid();
        vistas.add("Covid", vistaCovid);

        vistaWeather = new VistaWeather();
        vistas.add("Tiempo", vistaWeather);

        getContentPane().add(vistas);

    }

    public static void main(String[] args){
        new VentanaPrincipal();
    }

}
