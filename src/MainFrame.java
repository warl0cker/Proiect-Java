import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    public final JPanel cardPanel;
    public final CardLayout cardLayout;

    public MainFrame() {
        setTitle("Bine ati venit la Bar-Restaurant Proiect!");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JPanel fereastraPrincipala = new FereastraPrincipala();
        JPanel managerLogin = new ManagerLogin();
        JPanel managerFrame = new ManagerFrame();
        JPanel chelnerLogin = new ChelnerLogin();
        JPanel chelnerFrame = new ChelnerFrame();

        cardPanel.add(fereastraPrincipala, "fereastraPrincipala");
        cardPanel.add(managerLogin, "managerLogin");
        cardPanel.add(managerFrame, "managerFrame");
        cardPanel.add(chelnerLogin, "chelnerLogin");
        cardPanel.add(chelnerFrame, "chelnerFrame");

        FereastraPrincipala.colorTables();
        cardLayout.show(cardPanel, "fereastraPrincipala");
        add(cardPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static MainFrame getInstance() {
        setDefaultLookAndFeelDecorated(true);
        if (instance == null) instance = new MainFrame();
        return instance;
    }
}