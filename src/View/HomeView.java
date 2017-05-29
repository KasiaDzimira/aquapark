package View;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {

    final static String TAB_HOME = "HOME";
    final static String TAB_BUY = "BUY";
    final static String TAB_HISTORY = "HISTORY";
    final static String TAB_ACCOUNT = "ACCOUNT";

    private Dimension windowSize;

    public HomeView(String windowTitle) {
        this.windowSize = new Dimension(1200, 1000);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.centeringWindow();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void addComponentToPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel homeCard = new JPanel();
        homeCard.add(new JButton("homeCardButton"));

        JPanel buyCard = new JPanel();
        buyCard.add(new Button("buyCardButton"));

        JPanel historyCard = new JPanel();
        historyCard.add(new Button("historyCardButton"));

        JPanel accountCard = new JPanel();
        accountCard.add(new Button("accountCardButton"));

        tabbedPane.addTab(TAB_HOME, homeCard);
        tabbedPane.addTab(TAB_BUY, buyCard);
        tabbedPane.addTab(TAB_HISTORY, historyCard);
        tabbedPane.addTab(TAB_ACCOUNT, accountCard);

        Container pane = this.getContentPane();
        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    private void centeringWindow() {
        // place window in the center
        Dimension center = new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 2);
        this.setLocation(
                (int) (center.getWidth() - (this.windowSize.getWidth() / 2)),
                (int) (center.getHeight() - (this.windowSize.getHeight() / 2)));
    }
}
