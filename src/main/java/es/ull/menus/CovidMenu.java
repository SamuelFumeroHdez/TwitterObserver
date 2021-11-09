package es.ull.menus;

import es.ull.charts.GraphicContext;
import es.ull.charts.LineChartStrategy;
import es.ull.charts.PieChartStrategy;
import es.ull.controllers.CovidController;
import es.ull.controllers.WeatherController;
import es.ull.models.CovidModel;
import es.ull.models.WeatherModel;
import es.ull.views.VistaCovid;
import es.ull.views.VistaWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CovidMenu extends JInternalFrame implements ActionListener {

    private JMenuBar mb;
    private JMenu representar;
    private JMenuItem mundo, compararPaises, spain, eeuu, russia, brazil, india, uk;

    private VistaCovid vistaCovid;

    public CovidMenu(VistaCovid vistaCovid) {
        super("Menu");

        this.vistaCovid = vistaCovid;
        setVisible(true);

        setLayout(new BorderLayout());

        mb = new JMenuBar();
        representar = new JMenu("Representar");
        mb.add(representar);
        mundo = new JMenuItem("Mundo");
        compararPaises = new JMenuItem("Comparar países");
        spain = new JMenuItem("España");
        eeuu = new JMenuItem("Estados Unidos");
        russia = new JMenuItem("Rusia");
        brazil = new JMenuItem("Brasil");
        india = new JMenuItem("India");
        uk = new JMenuItem("Reino Unido");


        representar.add(mundo);
        representar.add(compararPaises);
        representar.add(spain);
        representar.add(eeuu);
        representar.add(russia);
        representar.add(brazil);
        representar.add(india);
        representar.add(uk);

        mundo.addActionListener(this);
        compararPaises.addActionListener(this);
        spain.addActionListener(this);
        eeuu.addActionListener(this);
        russia.addActionListener(this);
        brazil.addActionListener(this);
        india.addActionListener(this);
        uk.addActionListener(this);

        add(mb,BorderLayout.NORTH);
        pack();

    }

    private void activarGrafico(String country){
        GraphicContext gc;
        CovidModel model = new CovidModel(country);
        String title = "Covid en " + country;
        gc = new GraphicContext(title);
        JInternalFrame internal;
        if(country.equalsIgnoreCase("Compare")){
            gc.setStrategy(new PieChartStrategy(title, title, "Elementos", "Valor"));
            internal = new JInternalFrame("Comparativa de casos en el mundo - Covid");
        }else{
            gc.setStrategy(new LineChartStrategy(title, title, "Elementos", "Valor"));
            internal = new JInternalFrame("Covid en " + country);
        }


        internal.add(gc.getChartPanel());
        internal.setSize(500,250);
        internal.setResizable(true);
        internal.setClosable(true);
        internal.setVisible(true);
        CovidController covidController = new CovidController(gc,model,internal);
        Thread controllerThread = new Thread(covidController);
        controllerThread.start();

        this.vistaCovid.add(internal);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mundo){
            activarGrafico("World");
        }else if(e.getSource()==spain){
            activarGrafico("Spain");
        }else if(e.getSource()==eeuu){
            activarGrafico("US");
        }else if (e.getSource()==brazil){
            activarGrafico("Brazil");
        }else if(e.getSource()==uk){
            activarGrafico("United Kingdom");
        }else if(e.getSource()==india){
            activarGrafico("India");
        }else if(e.getSource()==russia){
            activarGrafico("Russia");
        }else if(e.getSource()==compararPaises){
            activarGrafico("Compare");
        }
    }
}
