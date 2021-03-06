package View;

import Controller.UserController;
import View.UserProfile.AccountView;
import View.UserProfile.BuyView;
import View.UserProfile.HistoryView;
import View.UserProfile.HomeView;

import Model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Main User view
 */
public class UserProfileView extends JFrame {

    final static String TAB_HOME = "HOME";
    final static String TAB_BUY = "BUY";
    final static String TAB_HISTORY = "HISTORY";
    final static String TAB_ACCOUNT = "ACCOUNT";

    /**
     * User
     */
    private User user;
    /**
     * Controller for user
     */
    private UserController userController;
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * User nick
     */
    private String userNick;

    /**
     * Constructor for UserProfileView
     * @param windowTitle Title of the window
     * @param user Active user
     * @param userNick User's nick
     * @param userController Controller for active user
     */
    public UserProfileView(String windowTitle, String userNick, User user, UserController userController) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        this.windowSize = new Dimension(((int) width)-10, ((int) height) - 10);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.centeringWindow();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.userNick = userNick;

        this.userController = userController;
        this.user = user;
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    public void addComponentToPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel buyCard = new JPanel();
        buyCard.add(new Button("buyCardButton"));

        JPanel historyCard = new JPanel();
        historyCard.add(new Button("historyCardButton"));

        JPanel accountCard = new JPanel();
        accountCard.add(new Button("accountCardButton"));

        HomeView homeView = new HomeView();
        //Delete arguments ~F
        AccountView accountView = new AccountView(user, userController);
        HistoryView historyView = new HistoryView();
        BuyView buyView = new BuyView();

        tabbedPane.addTab(TAB_HOME, homeView.renderView());
        tabbedPane.addTab(TAB_BUY, buyView.renderView());
        tabbedPane.addTab(TAB_HISTORY, historyView.renderView());
        tabbedPane.addTab(TAB_ACCOUNT, accountView.renderView());

        tabbedPane.setTabComponentAt(0, createLabel(TAB_HOME));
        tabbedPane.setTabComponentAt(1, createLabel(TAB_BUY));
        tabbedPane.setTabComponentAt(2, createLabel(TAB_HISTORY));
        tabbedPane.setTabComponentAt(3, createLabel(TAB_ACCOUNT));
        tabbedPane.setBackground(Color.decode("#acf0f2"));

        Container pane = this.getContentPane();
        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates label
     */
    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(273, 50));

        return label;
    }

    /**
     * Centers the window
     */
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
