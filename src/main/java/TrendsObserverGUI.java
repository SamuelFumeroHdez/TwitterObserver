import javax.swing.*;

public class TrendsObserverGUI extends JPanel{
    private JLabel twitterTrendsTxt;
    private JPanel trendsObserverPanel;

    TrendsObserverGUI(){

    }

    public JPanel getTrendsObserverPanel(){
        return this.trendsObserverPanel;
    }

}

class TrendsObserverGuiFrame extends JFrame{
    TrendsObserverGUI trendsObserverGUI = new TrendsObserverGUI();
    TrendsObserverGuiFrame(){
        super("Tendencias en Twitter");
        setContentPane(trendsObserverGUI.getTrendsObserverPanel());
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}


