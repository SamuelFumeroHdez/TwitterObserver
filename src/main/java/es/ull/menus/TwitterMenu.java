package es.ull.menus;

import es.ull.charts.BarChartStrategy;
import es.ull.charts.GraphicContext;
import es.ull.models.Model;
import es.ull.controllers.Controller;
import es.ull.views.VistaTwitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitterMenu extends JInternalFrame implements ActionListener{
    private JMenuBar mb;
    private JMenu representar, listarTendencias;
    private JMenuItem tendenciasTwitterEspana, tendenciasTwitterMexico, tendenciasTwitterEEUU;



    private VistaTwitter vistaTwitter;

    public TwitterMenu(VistaTwitter vistaTwitter){
        super("Menu");

        this.vistaTwitter = vistaTwitter;
        setVisible(true);


        setLayout( new BorderLayout());

        mb = new JMenuBar();
        representar = new JMenu("Representar");
        mb.add(representar);
        listarTendencias = new JMenu("Listar tendencias en...");
        representar.add(listarTendencias);
        tendenciasTwitterEspana = new JMenuItem("España");
        listarTendencias.add(tendenciasTwitterEspana);
        tendenciasTwitterMexico = new JMenuItem("Mexico");
        listarTendencias.add(tendenciasTwitterMexico);
        tendenciasTwitterEEUU = new JMenuItem("Estados Unidos");
        listarTendencias.add(tendenciasTwitterEEUU);

        tendenciasTwitterEspana.addActionListener(this);
        tendenciasTwitterEEUU.addActionListener(this);
        tendenciasTwitterMexico.addActionListener(this);

        add(mb,BorderLayout.NORTH);
        pack();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("algo ha ocurrido");
        if(e.getSource()==tendenciasTwitterEspana){
            System.out.println("Tendencias España");
            GraphicContext gc;
            int woeid = 23424950;
            Model model = new Model(woeid);
            String title = "Tendencias en España";
            gc = new GraphicContext(title);
            gc.setStrategy(new BarChartStrategy(title, title, "España", "Trends",true));

            Thread modelThread = new Thread(model);
            modelThread.start();



            JInternalFrame internal = new JInternalFrame("Tendencias en España");
            internal.add(gc.getChartPanel());
            internal.setSize(500,250);
            internal.setResizable(true);
            internal.setClosable(true);
            internal.setVisible(true);
            Controller twitterController = new Controller(gc,model,internal);
            Thread controllerThread = new Thread(twitterController);
            controllerThread.start();

            this.vistaTwitter.add(internal);


        }else if(e.getSource()==tendenciasTwitterMexico){
            System.out.println("Tendencias México");
            GraphicContext gc;
            int woeid = 23424900;
            Model model = new Model(woeid);
            String title = "Tendencias en México";
            gc = new GraphicContext(title);
            gc.setStrategy(new BarChartStrategy(title, title, "México", "Trends",true));

            Thread modelThread = new Thread(model);
            modelThread.start();

            JInternalFrame internal = new JInternalFrame("Tendencias en México");
            internal.add(gc.getChartPanel());
            internal.setSize(500,250);
            internal.setResizable(true);
            internal.setClosable(true);
            internal.setVisible(true);
            Controller twitterController = new Controller(gc,model,internal);
            Thread controllerThread = new Thread(twitterController);
            controllerThread.start();

            this.vistaTwitter.add(internal);
        }else if(e.getSource()==tendenciasTwitterEEUU){
            System.out.println("Tendencias EEUU");
            GraphicContext gc;
            int woeid = 23424977;
            Model model = new Model(woeid);
            String title = "Tendencias en Estados Unidos";
            gc = new GraphicContext(title);
            gc.setStrategy(new BarChartStrategy(title, title, "Estados Unidos", "Trends",true));

            Thread modelThread = new Thread(model);
            modelThread.start();

            JInternalFrame internal = new JInternalFrame("Tendencias en Estados Unidos");
            internal.add(gc.getChartPanel());
            internal.setSize(500,250);
            internal.setResizable(true);
            internal.setClosable(true);
            internal.setVisible(true);
            Controller twitterController = new Controller(gc,model,internal);
            Thread controllerThread = new Thread(twitterController);
            controllerThread.start();

            this.vistaTwitter.add(internal);
        }
    }
}
