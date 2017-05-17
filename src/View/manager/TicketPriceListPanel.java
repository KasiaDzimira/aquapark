package View.manager;

import Controller.TicketPriceListController;
import Controller.TicketPriceListPositionController;
import Model.TicketPriceList;
import Model.TicketPriceListPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by blurpek on 17.05.17.
 */
public class TicketPriceListPanel extends JPanel {
    private JList<TicketPriceListPosition> allPostition;
    private JList<TicketPriceListPosition> choosenPosition;
    private DefaultListModel<TicketPriceListPosition> allPositionModel;
    private DefaultListModel<TicketPriceListPosition> choosenPositionModel;
    private CreateTicketPriceListPositionPanel createTicketPriceListPositionPanel;

    private TicketPriceList ticketPriceList;
    private TicketPriceListController ticketPriceListController;
    private TicketPriceListPositionController ticketPriceListPositionController;

    public TicketPriceListPanel() {
        ticketPriceListController = new TicketPriceListController();
        ticketPriceListPositionController = new TicketPriceListPositionController();
        ticketPriceList = ticketPriceListController.getAllTicketPriceLists().get(0);
        createTicketPriceListPositionPanel = new CreateTicketPriceListPositionPanel(ticketPriceList);

        prepareGui();
    }

    private void prepareGui() {
        prepareLists();
        JPanel upPanel = createUpPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(upPanel);
        this.add(new JLabel("CREATE POSITION:"));
        this.add(createTicketPriceListPositionPanel);
    }

    private JPanel createUpPanel() {
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPositions();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePositions();
            }
        });

        panel.setLayout(new FlowLayout());
        panel.add(allPostition);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        panel.add(buttonsPanel);
        panel.add(choosenPosition);

        return panel;
    }

    private void removePositions() {

    }

    private void addPositions() {

    }

    private void prepareLists() {
        allPositionModel = new DefaultListModel<>();
        choosenPositionModel = new DefaultListModel<>();

//        for (TicketPriceListPosition position : ticketPriceListPositionController.getAllTicketPriceListPositions()) {
//            allPositionModel.addElement(position);
//        }
//        for (TicketPriceListPosition position : ticketPriceListPositionController.getAllTicketPriceListPositionsByTicketPriceList(ticketPriceList.getId())) {
//            choosenPositionModel.addElement(position);
//        }

        allPostition = new JList<>(allPositionModel);
        choosenPosition = new JList<>(choosenPositionModel);

//        attractionTypeListModel = new DefaultListModel();
//        attractionListModel = new DefaultListModel();
//
//        //load data type
//        mockUp();
////        loadFromDB();
//
//        attractionTypeList = new JList<>(attractionTypeListModel);
//        attractionList = new JList<>(attractionListModel);
//        attractionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
