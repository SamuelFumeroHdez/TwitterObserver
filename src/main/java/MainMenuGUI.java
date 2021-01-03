import javax.swing.*;

public class MainMenuGUI extends JPanel{
    private JButton observarTendenciasButton;
    private JButton monitorearUnaTendenciaButton;
    private JLabel twitterObserverTxt;
    private JPanel panelTwitterObserver;

    MainMenuGUI(){

    }

    public JPanel getPanel(){
        return this.panelTwitterObserver;
    }
}

class MainMenuGuiFrame extends JFrame{
    MainMenuGUI mainMenuGUI = new MainMenuGUI();
    MainMenuGuiFrame(){
        super("Twitter Observer");
        setContentPane(mainMenuGUI.getPanel());
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}


