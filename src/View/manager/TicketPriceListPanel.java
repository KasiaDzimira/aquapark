package View.manager;

import Controller.TicketPriceListController;
import Controller.TicketPriceListPositionController;
import Model.TicketPriceList;
import Model.TicketPriceListPosition;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TicketPriceListPanel extends JPanel {
    private JList<TicketPriceListPosition> allPostition;
    private JList<TicketPriceListPosition> choosenPosition;
    private DefaultListModel<TicketPriceListPosition> allPositionModel;
    private DefaultListModel<TicketPriceListPosition> choosenPositionModel;
    private CreateTicketPriceListPositionPanel createTicketPriceListPositionPanel;
    private JComboBox<TicketPriceListPosition> ticketPriceListPositionJComboBox;

    private String[] columnNames = {
            "Day",
            "Discount group",
            "Day time",
            "Attraction type",
            "Price"
    };

    private Object[][] tableData;

    private TicketPriceList ticketPriceList;
    private TicketPriceListController ticketPriceListController;
    private TicketPriceListPositionController ticketPriceListPositionController;
    private ArrayList<TicketPriceListPosition> ticketPriceListPositions;
    private ArrayList<TicketPriceListPosition> allTicketPositions;
    private GridBagConstraints gridBagConstraints;

    public TicketPriceListPanel() {
        ticketPriceListController = new TicketPriceListController();
        ticketPriceListPositionController = new TicketPriceListPositionController();
        ticketPriceList = ticketPriceListController.getAllTicketPriceLists().get(0);
        createTicketPriceListPositionPanel = new CreateTicketPriceListPositionPanel(ticketPriceList);
        ticketPriceListPositions = new ArrayList<>();
        allTicketPositions = new ArrayList<>();

        prepareLists();
        TicketPriceListPosition[] ticketPriceListPositions1 = allTicketPositions.toArray(new TicketPriceListPosition[allTicketPositions.size()]);
        ticketPriceListPositionJComboBox = new JComboBox<TicketPriceListPosition>(ticketPriceListPositions1);

        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        prepareGui();
    }

    private void prepareGui() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Ticket price list management:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel comboBoxPanel = new JPanel(new GridLayout(2, 1));
        JLabel comboBoxLabel = new JLabel("All positions (day, discount group, day time, attraction type, price):");

        JPanel inputPanel = new JPanel(new GridBagLayout());
        JButton addButton = new JButton("Add selected position");
        addButton.setBackground(new Color(235, 127, 0));
        addButton.setForeground(Color.WHITE);

        prepareLists();
        JPanel tablePanel = new JPanel(new GridBagLayout());
        JTable choosenPositionTable = new JTable(tableData, columnNames);
        choosenPositionTable.setPreferredSize(new Dimension(800, 200));

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 80));

        comboBoxPanel.setBackground(Color.WHITE);
        comboBoxPanel.setPreferredSize(new Dimension(800, 80));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(400, 80));

        tablePanel.setBackground(Color.WHITE);
        tablePanel.setPreferredSize(new Dimension(800, 200));

        ticketPriceListPositionJComboBox.setPreferredSize(new Dimension(400, 70));
        addButton.setPreferredSize(new Dimension(250, 50));
        choosenPositionTable.setPreferredSize(new Dimension(800, 150));

        detailsLabelPanel.add(attractionDetailsLabel);
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(ticketPriceListPositionJComboBox);

        GridBagConstraints gridBagConstraintsBtn = new GridBagConstraints();
        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        inputPanel.add(addButton, gridBagConstraintsBtn);

        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        tablePanel.add(choosenPositionTable, gridBagConstraintsBtn);

//        JPanel upPanel = createUpPanel();
//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                validateAndSave();
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        this.add(upPanel);
//        this.add(saveButton);
//        this.add(new JLabel("CREATE POSITION:"));
//        this.add(createTicketPriceListPositionPanel);

        gridBagConstraints.gridy = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 80, 0);
        this.add(new JSeparator(), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 50, 0);
        this.add(comboBoxPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(tablePanel, gridBagConstraints);
        this.setVisible(true);
    }

    private void setDefaultsComboBoxValue() {

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
        List<TicketPriceListPosition> list = ticketPriceListPositionController.getAllTicketPriceListPositionsByTicketPriceList(ticketPriceList.getId());
        int listSize = list.size();
        int i = 0;
        tableData = new Object[listSize][5];

        for (TicketPriceListPosition position : ticketPriceListPositionController.getAllTicketPriceListPositions()) {
            allPositionModel.addElement(position);
            allTicketPositions.add(position);
        }
        for (TicketPriceListPosition position : list) {
            choosenPositionModel.addElement(position);
            ticketPriceListPositions.add(position);
            tableData[i][0] = position.getDay().getName();
            tableData[i][1] = position.getDiscountGroup().getName();
            tableData[i][2] = position.getDaytime().getName();
            tableData[i][3] = position.getAttractionType().getName();
            tableData[i][4] = position.getPrice();
            i++;
        }

        allPostition = new JList<>(allPositionModel);
        choosenPosition = new JList<>(choosenPositionModel);
    }
}
