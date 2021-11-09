package es.ull.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LineChartStrategy extends ApplicationFrame implements IChartStrategy{

    String chartTitle;
    String etiqueta;

    private JPanel grafica = new JPanel();

    public LineChartStrategy(String applicationTitle, String chartTitle, String xAxisName, String yAxisName) {
        super(applicationTitle);
        this.chartTitle = chartTitle;
        this.etiqueta = xAxisName;

    }

    @Override
    public Object createDataset(ArrayList<String> valoresX, ArrayList<String> valoresY) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int index = 0;
        for(String str : valoresX){
            System.out.println(str);
            System.out.println(valoresY.get(index));
            dataset.addValue(Double.parseDouble(valoresY.get(index)),this.etiqueta,str);
            index++;
        }
        return dataset;
    }

    @Override
    public void buildChart(ArrayList<String> valoresX, ArrayList<String> valoresY, String xAxisName, String yAxisName) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                xAxisName,yAxisName,
                (CategoryDataset) createDataset(valoresX, valoresY),
                PlotOrientation.VERTICAL,
                true,true,false);

        CategoryPlot plot = lineChart.getCategoryPlot();
        Font fontAxis = new Font("Dialog", Font.PLAIN, 25);
        Font fontItems = new Font("Dialog", Font.PLAIN, 12);
        plot.getDomainAxis().setLabelFont(fontAxis);
        plot.getRangeAxis().setLabelFont(fontAxis);
        plot.getDomainAxis().setTickLabelFont(fontItems);

        grafica = new ChartPanel( lineChart );
        setContentPane( grafica );
    }

    @Override
    public JPanel getChartPanel() {
        return this.grafica;
    }
}
