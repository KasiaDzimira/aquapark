package View.manager;

import Model.TicketPriceList;
import View.UserProfile.AccountView;
import View.UserProfile.BuyView;
import View.UserProfile.HistoryView;
import View.UserProfile.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main manager view
 */
public class ManagerView extends JFrame {

    final static String TAB_CREATE_ATTRACTION = "CREATE ATTRACTION";
    final static String TAB_CREATE_ATTRACTION_TYPE = "CREATE ATTRACTION TYPE";
    final static String TAB_UPDATE_ATTRACTION_TYPE = "UPDATE ATTRACTION TYPE";
    final static String TAB_UPDATE_ATTRACTION = "UPDATE ATTRACTION";
    final static String TAB_TICKET_PRICE_LIST = "TICKET PRICE LIST";
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * Panel with buttons
     */
    private JPanel buttonsPanel;
    /**
     * Panel with cards
     */
    private JPanel cardsPanel;
    /**
     * Panel with attraction creation
     */
    private CreateAttractionPanel createAttraction;
    /**
     * Panel with attraction type creation
     */
    private CreateAttractionTypePanel createAttractionTypePanel;
    /**
     * Panel with attraction type update
     */
    private UpdateAttractionTypePanel updateAttractionTypePanel;
    /**
     * Panel with attraction update
     */
    private UpdateAttractionPanel updateAttractionPanel;
    /**
     * Panel with ticket prices
     */
    private TicketPriceListPanel ticketPriceListPanel;

    /**
     * Constructor for ManagerView
     * Adjusts settings of elements to display
     * @param title Title of window
     */
    public ManagerView(String title) {
        this.windowSize = new Dimension(1100, 700);
        this.setTitle(title);
        this.setSize(this.windowSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.centeringWindow();
        buttonsPanel = new JPanel();
        cardsPanel = new JPanel();
        createAttraction = new CreateAttractionPanel();
        createAttractionTypePanel = new CreateAttractionTypePanel();
        updateAttractionTypePanel = new UpdateAttractionTypePanel();
        updateAttractionPanel = new UpdateAttractionPanel();
        ticketPriceListPanel = new TicketPriceListPanel();
        this.addComponentToPane();
    }

    /**
     * Creates every element of page and adds it to main pane
     */
    private void addComponentToPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel buyCard = new JPanel();
        buyCard.add(new Button("buyCardButton"));

        JPanel historyCard = new JPanel();
        historyCard.add(new Button("historyCardButton"));

        JPanel accountCard = new JPanel();
        accountCard.add(new Button("accountCardButton"));

        createAttraction.setBackground(Color.WHITE);
        createAttractionTypePanel.setBackground(Color.WHITE);
        updateAttractionPanel.setBackground(Color.white);
        updateAttractionTypePanel.setBackground(Color.white);
        ticketPriceListPanel.setBackground(Color.WHITE);

        tabbedPane.addTab(TAB_CREATE_ATTRACTION, createAttraction);
        tabbedPane.addTab(TAB_CREATE_ATTRACTION_TYPE, createAttractionTypePanel);
        tabbedPane.addTab(TAB_UPDATE_ATTRACTION_TYPE, updateAttractionTypePanel);
        tabbedPane.addTab(TAB_UPDATE_ATTRACTION, updateAttractionPanel);
        tabbedPane.addTab(TAB_TICKET_PRICE_LIST, ticketPriceListPanel);

        tabbedPane.setTabComponentAt(0, createLabel(TAB_CREATE_ATTRACTION));
        tabbedPane.setTabComponentAt(1, createLabel(TAB_CREATE_ATTRACTION_TYPE));
        tabbedPane.setTabComponentAt(2, createLabel(TAB_UPDATE_ATTRACTION_TYPE));
        tabbedPane.setTabComponentAt(3, createLabel(TAB_UPDATE_ATTRACTION));
        tabbedPane.setTabComponentAt(4, createLabel(TAB_TICKET_PRICE_LIST));
        tabbedPane.setBackground(new Color(172, 240, 242));

        Container pane = this.getContentPane();
        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates label
     */
    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(190, 40));

        return label;
    }

    /**
     * Shows window
     */
    public void runWelcome() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setVisible(true);
    }

    /**
     * Centers window
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
