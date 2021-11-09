package es.ull.controllers;


import es.ull.charts.GraphicContext;
import es.ull.models.Model;

import javax.swing.*;

public class Controller implements Runnable {

    private GraphicContext gc;
    private Model twitterModel;
    private JInternalFrame cuadroGRafico;

    public Controller(GraphicContext gc, Model tm, JInternalFrame cuadroGrafico){
        this.gc = gc;
        this.twitterModel = tm;
        this.cuadroGRafico = cuadroGrafico;
    }

    @Override
    public void run() {
        int cont;
        boolean change;
        for(;;){
            try {
                Thread.sleep(15000);
                cont = 0;
                change = false;
                while(!change && cont<this.twitterModel.getTrendList().size()){
                    if(this.twitterModel.getTrendList().get(cont).getName().equalsIgnoreCase(this.twitterModel.getOldTrendList().get(cont).getName()) ||
                            this.twitterModel.getTrendList().get(cont).getTweetVolume() != this.twitterModel.getOldTrendList().get(cont).getTweetVolume()){
                        change = true;
                    }
                    cont++;
                }
                if (change){
                    gc.buildChart(twitterModel.arrayDatosEjes.get(0),twitterModel.arrayDatosEjes.get(1),"Trends","Volume");
                    this.cuadroGRafico.getContentPane().removeAll();
                    this.cuadroGRafico.getContentPane().add(gc.getChartPanel());
                    /*System.out.println(PruebaInternalFrame.getParejasVentanasControladores());
                    PruebaInternalFrame.getParejasVentanasControladores().get(this).getContentPane().removeAll();
                    PruebaInternalFrame.getParejasVentanasControladores().get(this).getContentPane().add(gc.getChartPanel());*/
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
