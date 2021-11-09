package es.ull.menus;

import es.ull.charts.BarChartStrategy;
import es.ull.charts.GraphicContext;
import es.ull.charts.LineChartStrategy;
import es.ull.controllers.WeatherController;
import es.ull.models.WeatherModel;
import es.ull.views.VistaWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherMenu extends JInternalFrame implements ActionListener {

    private JMenuBar mb;
    private JMenu representar;
    private JMenuItem madrid, londres, tokio, washington, paris;

    private VistaWeather vistaWeather;

    public WeatherMenu(VistaWeather vistaWeather) {
        super("Menu");

        this.vistaWeather = vistaWeather;
        setVisible(true);
        setLayout(new BorderLayout());

        mb = new JMenuBar();
        representar = new JMenu("Representar");
        mb.add(representar);
        madrid = new JMenuItem("Madrid");
        londres = new JMenuItem("Londres");
        tokio = new JMenuItem("Tokio");
        washington = new JMenuItem("Washington");
        paris = new JMenuItem("Paris");

        representar.add(madrid);
        representar.add(londres);
        representar.add(tokio);
        representar.add(washington);
        representar.add(paris);

        madrid.addActionListener(this);
        londres.addActionListener(this);
        tokio.addActionListener(this);
        washington.addActionListener(this);
        paris.addActionListener(this);

        add(mb,BorderLayout.NORTH);
        pack();

    }

    private void activarGrafico(String location){
        GraphicContext gc;
        WeatherModel model = new WeatherModel(location);
        String title = "Tiempo en " + location;
        gc = new GraphicContext(title);
        gc.setStrategy(new BarChartStrategy(title, title, "Elementos", "Valor",false));

        Thread modelThread = new Thread(model);
        modelThread.start();



        JInternalFrame internal = new JInternalFrame("Tiempo en " + location);
        internal.add(gc.getChartPanel());
        internal.setSize(500,250);
        internal.setResizable(true);
        internal.setClosable(true);
        internal.setVisible(true);
        WeatherController weatherController = new WeatherController(gc,model,internal);
        Thread controllerThread = new Thread(weatherController);
        controllerThread.start();

        this.vistaWeather.add(internal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==madrid){
            System.out.println("Tiempo Madrid");
            activarGrafico("Madrid");

        }else if(e.getSource()==londres){
            activarGrafico("Londres");
        }else if(e.getSource()==tokio){
            activarGrafico("Tokio");
        }else if (e.getSource()==washington){
            activarGrafico("Washington");
        }else if(e.getSource()==paris){
            activarGrafico("Paris");
        }
    }
}
