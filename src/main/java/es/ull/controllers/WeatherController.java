package es.ull.controllers;

import es.ull.charts.GraphicContext;
import es.ull.models.Model;
import es.ull.models.WeatherModel;

import javax.swing.*;

public class WeatherController implements Runnable{

    private GraphicContext gc;
    private WeatherModel weatherModel;
    private JInternalFrame cuadroGRafico;

    public WeatherController(GraphicContext gc, WeatherModel wm, JInternalFrame cuadroGrafico){
        this.gc = gc;
        this.weatherModel = wm;
        this.cuadroGRafico = cuadroGrafico;
    }
    @Override
    public void run() {

        for(;;){
            try {
                Thread.sleep(15000);
                gc.buildChart(this.weatherModel.arrayDatosEjes.get(0),this.weatherModel.arrayDatosEjes.get(1),"Elemento","Valor");
                this.cuadroGRafico.getContentPane().removeAll();
                this.cuadroGRafico.getContentPane().add(gc.getChartPanel());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
