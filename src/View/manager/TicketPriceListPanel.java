package View.manager;

import Controller.TicketPriceListController;
import Controller.TicketPriceListPositionController;
import Model.TicketPriceList;
import Model.TicketPriceListPosition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TicketPriceListPanel extends JPanel {
    private DefaultListModel<TicketPriceListPosition> allPositionModel;
    private DefaultListModel<TicketPriceListPosition> choosenPositionModel;
    private JComboBox<TicketPriceListPosition> ticketPriceListPositionJComboBox;
    private DefaultTableModel tableModel;

    private String[] columnNames = {
            "Day",
            "Discount group",
            "Day time",
            "Attraction type",
            "Price",
            "Position id"
    };

    private Object[][] tableData;

    private TicketPriceList ticketPriceList;
    private TicketPriceListController ticketPriceListController;
    private TicketPriceListPositionController ticketPriceListPositionController;
    private ArrayList<TicketPriceListPosition> ticketPriceListPositions;
    private ArrayList<TicketPriceListPosition> allTicketPositions;
    private GridBagConstraints gridBagConstraints;
    private JTable choosenPositionTable;

    public TicketPriceListPanel() {
        ticketPriceListController = new TicketPriceListController();
        ticketPriceListPositionController = new TicketPriceListPositionController();
        ticketPriceList = ticketPriceListController.getAllTicketPriceLists().get(0);
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
        JButton addButton = new JButton("Add selected to price list");
        addButton.setBackground(new Color(235, 127, 0));
        addButton.setForeground(Color.WHITE);

        JButton showTicketListButton = new JButton("Price list");
        showTicketListButton.setBackground(new Color(235, 127, 0));
        showTicketListButton.setForeground(Color.WHITE);

        JButton createPositionButton = new JButton("Create new position");
        createPositionButton.setBackground(new Color(235, 127, 0));
        createPositionButton.setForeground(Color.WHITE);

        prepareLists();
        JPanel tablePanel = new JPanel(new GridLayout(1, 0));
        this.tableModel = new DefaultTableModel(tableData, columnNames);
        this.choosenPositionTable = new JTable(tableModel);
        choosenPositionTable.setPreferredSize(new Dimension(800, 200));

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 80));

        comboBoxPanel.setBackground(Color.WHITE);
        comboBoxPanel.setPreferredSize(new Dimension(800, 80));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(400, 250));

        tablePanel.setBackground(Color.WHITE);
        tablePanel.setPreferredSize(new Dimension(800, 200));

        ticketPriceListPositionJComboBox.setPreferredSize(new Dimension(400, 70));
        addButton.setPreferredSize(new Dimension(250, 50));
        showTicketListButton.setPreferredSize(new Dimension(250, 50));
        createPositionButton.setPreferredSize(new Dimension(250, 50));
        choosenPositionTable.setPreferredSize(new Dimension(750, 150));

        detailsLabelPanel.add(attractionDetailsLabel);
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(ticketPriceListPositionJComboBox);

        GridBagConstraints gridBagConstraintsBtn = new GridBagConstraints();
        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        gridBagConstraintsBtn.insets = new Insets(0, 0, 20, 0);
        inputPanel.add(createPositionButton, gridBagConstraintsBtn);
        gridBagConstraintsBtn.gridy = 1;
        inputPanel.add(addButton, gridBagConstraintsBtn);
        gridBagConstraintsBtn.gridy = 2;
        inputPanel.add(showTicketListButton, gridBagConstraintsBtn);

        showTicketListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PriceListView priceListView = new PriceListView(choosenPositionTable);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPositions();
            }
        });

        createPositionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePositionView createPositionView = new CreatePositionView(ticketPriceList, ticketPriceListPositionJComboBox);
            }
        });

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
        this.setVisible(true);
    }

    private void addPositions() {
        Object[] newRow = new Object[6];
        TicketPriceListPosition selectedPosition = (TicketPriceListPosition) ticketPriceListPositionJComboBox.getSelectedItem();
        newRow[0] = selectedPosition.getDay().getName();
        newRow[1] = selectedPosition.getDiscountGroup().getName();
        newRow[2] = selectedPosition.getDaytime().getName();
        newRow[3] = selectedPosition.getAttractionType().getName();
        newRow[4] = selectedPosition.getPrice();
        newRow[5] = selectedPosition.getId();
        this.tableModel.addRow(newRow);
        this.choosenPositionTable = new JTable(this.tableModel);
        ticketPriceListPositionController.createTicketPriceListPosition(
                selectedPosition.getPrice(),
                ticketPriceList.getId(),
                selectedPosition.getDay().getId(),
                selectedPosition.getDiscountGroup().getId(),
                selectedPosition.getDaytime().getId(),
                selectedPosition.getAttractionType().getId()
        );
        JOptionPane.showMessageDialog(null, "Position has been successfully added to price list!");
    }

    private void prepareLists() {
        allPositionModel = new DefaultListModel<>();
        choosenPositionModel = new DefaultListModel<>();
        List<TicketPriceListPosition> list = ticketPriceListPositionController.getAllTicketPriceListPositionsByTicketPriceList(ticketPriceList.getId());
        int listSize = list.size();
        int i = 0;
        tableData = new Object[listSize][6];
        this.prepareTicketPositionList(allPositionModel);

        for (TicketPriceListPosition position : list) {
            choosenPositionModel.addElement(position);
            ticketPriceListPositions.add(position);
            tableData[i][0] = position.getDay().getName();
            tableData[i][1] = position.getDiscountGroup().getName();
            tableData[i][2] = position.getDaytime().getName();
            tableData[i][3] = position.getAttractionType().getName();
            tableData[i][4] = position.getPrice();
            tableData[i][5] = position.getId();
            i++;
        }
    }

    private void prepareTicketPositionList(DefaultListModel allPositionModel) {
        for (TicketPriceListPosition position : ticketPriceListPositionController.getAllTicketPriceListPositions()) {
            allPositionModel.addElement(position);
            allTicketPositions.add(position);
        }
    }
}
