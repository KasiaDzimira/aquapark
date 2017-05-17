package View.manager;

import Controller.TicketPriceListController;
import Controller.TicketPriceListPositionController;
import Model.TicketPriceList;
import Model.TicketPriceListPosition;

import javax.swing.*;

/**
 * Created by blurpek on 17.05.17.
 */
public class TicketPriceListPanel extends JPanel {
    private JList<TicketPriceListPosition> allPostition;
    private JList<TicketPriceListPosition> choosenPosition;

    private TicketPriceList ticketPriceList;
    private TicketPriceListController ticketPriceListController;
    private TicketPriceListPositionController ticketPriceListPositionController;

    public TicketPriceListPanel() {
        ticketPriceListController = new TicketPriceListController();
        ticketPriceListPositionController = new TicketPriceListPositionController();
        ticketPriceList = ticketPriceListController.getAllTicketPriceLists().get(0);
    }
}
