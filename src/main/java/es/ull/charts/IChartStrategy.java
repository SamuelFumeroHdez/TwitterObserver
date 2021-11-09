package es.ull.charts;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;

public interface IChartStrategy {
    Object createDataset(ArrayList<String> valoresX, ArrayList<String> valoresY);
    void buildChart(ArrayList<String> valoresX, ArrayList<String> valoresY, String xAxisName, String yAxisName);
    JPanel getChartPanel();

}
