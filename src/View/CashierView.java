package View;

import View.Cashier.*;

import javax.swing.*;
import java.awt.*;

/**
 * Cashier view
 */
public class CashierView extends JFrame {

    final static String TAB_CREATE = "PASS MANAGEMENT";
    final static String TAB_SELL_TICKET = "SELL TICKET";
    final static String TAB_RTICKET = "RETURN TICKET";

    /**
     * Dimensions of the window
     */
    private Dimension windowSize;

    /**
     * Constructor for CashierView
     * @param windowTitle Title of the window
     */
    public CashierView(String windowTitle) {
        this.windowSize = new Dimension(1100, 700);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.centeringWindow();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    public void runWelcome() {
        JTabbedPane tabbedPane = new JTabbedPane();


        JPanel buyCard = new JPanel();
        buyCard.add(new Button("createPassCardButton"));

        JPanel historyCard = new JPanel();
        historyCard.add(new Button("sellTicketCardButton"));

        JPanel accountCard = new JPanel();
        accountCard.add(new Button("returnTicketCardButton"));

        CreateNewPassView createPassView = new CreateNewPassView(); // buyView
        CreateNewTicketView createNewTicketView = new CreateNewTicketView();
        ReturnTicketView returnTicketView = new ReturnTicketView(); // accountView


        tabbedPane.addTab(TAB_CREATE, createPassView.renderWindow());
        tabbedPane.addTab(TAB_SELL_TICKET, createNewTicketView.renderWindow());
        tabbedPane.addTab(TAB_RTICKET, returnTicketView);

        tabbedPane.setTabComponentAt(0, createLabel(TAB_CREATE));
        tabbedPane.setTabComponentAt(1, createLabel(TAB_SELL_TICKET));
        tabbedPane.setTabComponentAt(2, createLabel(TAB_RTICKET));
        tabbedPane.setBackground(Color.decode("#acf0f2"));

        Container pane = this.getContentPane();
        pane.add(tabbedPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Creates label
     */
    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(230, 40));

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