package View.manager;

import Controller.TicketPriceListPositionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PriceListView extends JFrame {

    private Dimension windowSize;
    private JScrollPane pane;
    private TicketPriceListPositionController positionController;

    public PriceListView(JTable table) {
        this.positionController = new TicketPriceListPositionController();
        this.setTitle("Price list");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JButton removeButton = new JButton("Remove selected position");
        removeButton.setBackground(new Color(235, 127, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.setPreferredSize(new Dimension(250, 50));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePositions(table);
            }
        });

        this.pane = new JScrollPane(table);
        this.add(pane, BorderLayout.CENTER);
        this.add(removeButton, BorderLayout.SOUTH);
        this.windowSize = new Dimension(500, 400);
        this.setSize(this.windowSize);
        this.centeringWindow();
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

    private void removePositions(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        positionController.deleteTicketPriceListPosition((int) tableModel.getValueAt(table.getSelectedRow(), 5));
        tableModel.removeRow(table.getSelectedRow());
        table.clearSelection();
    }
}
