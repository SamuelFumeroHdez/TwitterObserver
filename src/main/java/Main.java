import java.util.HashMap;
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
        Timer t = new Timer();
        t.scheduleAtFixedRate(twitterSpainModel,0,5*1000);
    }
}
