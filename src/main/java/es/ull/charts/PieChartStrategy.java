package es.ull.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PieChartStrategy extends ApplicationFrame implements IChartStrategy{

    String chartTitle;
    String etiqueta;

    private JPanel grafica = new JPanel();

    public PieChartStrategy(String applicationTitle, String chartTitle, String xAxisName, String yAxisName) {
        super(applicationTitle);
        this.chartTitle = chartTitle;
        this.etiqueta = xAxisName;

    }

    @Override
    public Object createDataset(ArrayList<String> valoresX, ArrayList<String> valoresY) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int index = 0;
        for(String str : valoresX){
            System.out.println(str);
            System.out.println(valoresY.get(index));
            dataset.setValue(str + ", " +valoresY.get(index), Double.parseDouble(valoresY.get(index)));
            index++;
        }
        return dataset;
    }

    @Override
    public void buildChart(ArrayList<String> valoresX, ArrayList<String> valoresY, String xAxisName, String yAxisName) {

        JFreeChart pieChart = ChartFactory.createPieChart(
                chartTitle,
                (PieDataset) createDataset(valoresX,valoresY),

                true,true,false);

        grafica = new ChartPanel( pieChart );
        setContentPane( grafica );
    }

    @Override
    public JPanel getChartPanel() {
        return this.grafica;
    }
}