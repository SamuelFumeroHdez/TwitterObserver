import org.jfree.ui.RefineryUtilities;
import twitter4j.Trend;

import java.sql.Time;
import java.util.Timer;

public class Main {
    public static void main(String[] args){

        String myTrend = "Wuhan";
        int woeidSpain = 23424950;
        int woeidBarcelona = 753692;
        int woeidBilbao = 754542;
        int woeidLasPalmas = 764814;
        int woeidMadrid = 766273;


        Model twitterSpainModel = new Model(woeidSpain);
        /*Thread modelThread = new Thread(twitterSpainModel);
        modelThread.start();

        BarChartStrategy twitterTrendsChart = new BarChartStrategy(
                "Twitter Observer" ,
                "Tendencias en Twitter España", twitterSpainModel);


        RefineryUtilities.centerFrameOnScreen( twitterTrendsChart );



        Controller twitterController = new Controller(twitterTrendsChart,twitterSpainModel);
        Thread controllerThread = new Thread(twitterController);
        controllerThread.start();*/

        Trend trend = twitterSpainModel.getTrendList().get(0);
        System.out.println(twitterSpainModel.getTrendList().get(0));
        final String title = "Time Series Management";
        final TimeSeriesChartStrategy demo = new TimeSeriesChartStrategy( title, trend, twitterSpainModel );
        demo.pack( );
        RefineryUtilities.positionFrameRandomly( demo );
        Thread timeSeriesTrendChartThread = new Thread(demo);
        timeSeriesTrendChartThread.start();



    }
}
