import javax.swing.*;

public class TrendMonitoringGUI extends JPanel{
    private JLabel trendMonitoringTxt;
    private JPanel trendMonitoringPanel;

    TrendMonitoringGUI(){

    }

    public JPanel getTrendMonitoringPanel(){
        return this.trendMonitoringPanel;
    }

}

class TrendMenuGuiFrame extends JFrame{
    TrendMonitoringGUI trendMonitoringGUI = new TrendMonitoringGUI();
    TrendMenuGuiFrame(){
        super("Monitorización de una Tendencia");
        setContentPane(trendMonitoringGUI.getTrendMonitoringPanel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
