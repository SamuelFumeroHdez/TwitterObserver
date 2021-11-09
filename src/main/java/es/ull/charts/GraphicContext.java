package es.ull.charts;

import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.util.ArrayList;

public class GraphicContext extends ApplicationFrame {

    private IChartStrategy myIChartStrategy;

    public GraphicContext(String title) {
        super(title);
    }

    public Object createDataset(ArrayList<String> valoresX, ArrayList<String> valoresY){
        return myIChartStrategy.createDataset(valoresX, valoresY);
    }

    public void buildChart(ArrayList<String> valoresX, ArrayList<String> valoresY, String xAxisName, String yAxisName){
        myIChartStrategy.buildChart(valoresX, valoresY, xAxisName, yAxisName);
    }

    public void setStrategy(IChartStrategy iChartStrategy){
        this.myIChartStrategy = iChartStrategy;
    }

    public JPanel getChartPanel(){
        return this.myIChartStrategy.getChartPanel();
    }
}
