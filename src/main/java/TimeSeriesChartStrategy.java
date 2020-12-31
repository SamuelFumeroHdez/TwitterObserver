import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import twitter4j.Trend;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeSeriesChartStrategy extends ApplicationFrame implements Runnable{

    private HashMap<Double, Second> trendEvolution = new HashMap<>();
    private Trend trend;
    Model twitterModel;

    public TimeSeriesChartStrategy(String title, Trend trend, Model twitterModel) {
        super(title);
        this.trend = trend;
        this.twitterModel = twitterModel;
        builChart();
    }

    private XYDataset createDataset( ) {
        final TimeSeries series = new TimeSeries( "Random Data" );
        double value;
        /*for (int i = 0; i < timeList.size(); i++) {

            try {
                value = this.trend.getTweetVolume();
                series.add(timeList.get(i), new Double( value ) );
                System.out.println("Se han añadido datos");
                //current = ( Second ) current.next( );
            } catch ( SeriesException e ) {
                System.err.println("Error adding to series");
            }
        }*/
        this.trendEvolution.forEach((volume,second) -> series.add(second,volume));

        return new TimeSeriesCollection(series);
    }

    private JFreeChart createChart( final XYDataset dataset ) {
        return ChartFactory.createTimeSeriesChart(
                "Seguimiento de la tendencia 'Illa' en España",
                "Hora",
                "Volumen",
                dataset,
                false,
                false,
                false);
    }

    private void builChart(){
        final XYDataset dataset = createDataset( );
        final JFreeChart chart = createChart( dataset );
        final ChartPanel chartPanel = new ChartPanel( chart );

        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
        chartPanel.setMouseZoomable( true , false );
        setContentPane( chartPanel );
        setVisible(true);
    }

    @Override
    public void run() {
        for(;;){
            try {
                Thread.sleep(10000);

                System.out.println("Lista de tendencias -> " + twitterModel.getTrendList());
                System.out.println("Observando la tendencia: " + twitterModel.getTrendList().get(0));
                this.trend = twitterModel.getTrendList().get(0);
                this.trendEvolution.put((double)trend.getTweetVolume(),new Second());
                builChart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
