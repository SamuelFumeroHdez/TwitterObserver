import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import twitter4j.Trend;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

public class BarChartStrategy extends ApplicationFrame{

    private HashMap<String,Integer> trendsList = new HashMap<>();
    Model twitterModel;
    String chartTitle;

    public BarChartStrategy(String applicationTitle, String chartTitle, Model twitterModel) {
        super(applicationTitle);
        this.twitterModel = twitterModel;
        this.chartTitle = chartTitle;
        setVisible(true);
        setSize(1100,800);

    }

    private DefaultCategoryDataset createDataset( ArrayList<Trend> trendList) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(Trend trend : trendList){
            dataset.addValue((double)trend.getTweetVolume(),"Spain",trend.getName());
        }
        return dataset;
    }

    public void buildChart(ArrayList<Trend> trendList){
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Trend","Volume",
                createDataset(trendList),
                PlotOrientation.HORIZONTAL,
                true,true,false);

        CategoryPlot plot = barChart.getCategoryPlot();
        Font fontAxis = new Font("Dialog", Font.PLAIN, 25);
        Font fontItems = new Font("Dialog", Font.PLAIN, 12);
        plot.getDomainAxis().setLabelFont(fontAxis);
        plot.getRangeAxis().setLabelFont(fontAxis);
        plot.getDomainAxis().setTickLabelFont(fontItems);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
        this.setVisible(true);
        this.pack();
    }



}
