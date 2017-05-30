package View.manager;

import Controller.TicketPriceListController;
import Controller.TicketPriceListPositionController;
import Model.TicketPriceList;
import Model.TicketPriceListPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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
    private ArrayList<TicketPriceListPosition> ticketPriceListPositions;

    public TicketPriceListPanel() {
        ticketPriceListController = new TicketPriceListController();
        ticketPriceListPositionController = new TicketPriceListPositionController();
        ticketPriceList = ticketPriceListController.getAllTicketPriceLists().get(0);
        createTicketPriceListPositionPanel = new CreateTicketPriceListPositionPanel(ticketPriceList);
        ticketPriceListPositions = new ArrayList<>();
        prepareGui();
    }

    private void prepareGui() {
        prepareLists();
        JPanel upPanel = createUpPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndSave();
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(upPanel);
        this.add(saveButton);
        this.add(new JLabel("CREATE POSITION:"));
        this.add(createTicketPriceListPositionPanel);
    }

    private void validateAndSave() {
        for (int i = 0; i < choosenPositionModel.size(); i++) {
            if (ticketPriceListPositions.contains(choosenPositionModel.get(i))) {
                ticketPriceListPositions.remove(choosenPositionModel.get(i));
            }
            ticketPriceListPositionController.updateTicketPriceListPosition(choosenPositionModel.get(i).getId(), this.ticketPriceList.getId());
        }
        for (TicketPriceListPosition position : ticketPriceListPositions) {
            ticketPriceListPositionController.deleteTicketPriceListPosition(position.getId());
        }
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

        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.PAGE_AXIS));
        allPanel.add(new JLabel("All positions"));
        allPanel.add(allPostition);

        JPanel chosenPanel = new JPanel();
        chosenPanel.setLayout(new BoxLayout(chosenPanel, BoxLayout.PAGE_AXIS));
        chosenPanel.add(new JLabel("Position in ticket price list"));
        chosenPanel.add(choosenPosition);
        panel.setLayout(new FlowLayout());
        panel.add(chosenPanel);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        panel.add(buttonsPanel);
        panel.add(allPanel);

        return panel;
    }

    private void removePositions() {
        choosenPositionModel.remove(choosenPosition.getSelectedIndex());
    }

    private void addPositions() {
        choosenPositionModel.addElement(allPostition.getSelectedValue());
    }

    private void prepareLists() {
        allPositionModel = new DefaultListModel<>();
        choosenPositionModel = new DefaultListModel<>();

        for (TicketPriceListPosition position : ticketPriceListPositionController.getAllTicketPriceListPositions()) {
            allPositionModel.addElement(position);
        }
        for (TicketPriceListPosition position : ticketPriceListPositionController.getAllTicketPriceListPositionsByTicketPriceList(ticketPriceList.getId())) {
            choosenPositionModel.addElement(position);
            ticketPriceListPositions.add(position);
        }

        allPostition = new JList<>(allPositionModel);
        choosenPosition = new JList<>(choosenPositionModel);

    }
}
