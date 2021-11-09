package es.ull.controllers;

import es.ull.charts.GraphicContext;
import es.ull.models.CovidModel;
import es.ull.models.Model;
import es.ull.models.WeatherModel;

import javax.swing.*;

public class CovidController implements Runnable{

    private GraphicContext gc;
    private CovidModel covidModel;
    private JInternalFrame cuadroGRafico;

    public CovidController(GraphicContext gc, CovidModel cm, JInternalFrame cuadroGrafico){
        this.gc = gc;
        this.covidModel = cm;
        this.cuadroGRafico = cuadroGrafico;
    }
    @Override
    public void run() {

        for(;;){
            try {
                Thread.sleep(5000);
                gc.buildChart(this.covidModel.arrayDatosEjes.get(0),this.covidModel.arrayDatosEjes.get(1),"Elemento","Valor");
                this.cuadroGRafico.getContentPane().removeAll();
                this.cuadroGRafico.getContentPane().add(gc.getChartPanel());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}