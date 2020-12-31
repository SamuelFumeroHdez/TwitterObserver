import java.util.TimerTask;

public class Controller implements Runnable {

    private BarChartStrategy barChart;
    private Model twitterModel;

    public Controller(BarChartStrategy bcs, Model tm){
        this.barChart = bcs;
        this.twitterModel = tm;
    }

    @Override
    public void run() {
        int cont;
        boolean change;
        for(;;){
            try {
                Thread.sleep(15000);

                cont = 0;
                change = false;
                while(!change && cont<this.twitterModel.getTrendList().size()){
                    System.out.println("comparando");
                    System.out.println(this.twitterModel.getOldTrendList());
                    if(this.twitterModel.getTrendList().get(cont).getName().equalsIgnoreCase(this.twitterModel.getOldTrendList().get(cont).getName()) ||
                            this.twitterModel.getTrendList().get(cont).getTweetVolume() != this.twitterModel.getOldTrendList().get(cont).getTweetVolume()){
                        change = true;
                    }
                }
                if (change){
                    System.out.println("pintamos");
                    barChart.buildChart(twitterModel.getTrendList());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
