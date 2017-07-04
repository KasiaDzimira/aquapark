package View.Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import Controller.*;
import Model.*;

/**
 * Cashier view when user is returning the ticket
 */
public class ReturnTicketView extends JPanel {
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * Controller for watch
     */
    private WatchController watchController;
    /**
     * Controller for day
     */
    private DayController dayController;
    /**
     * Controller for daytime
     */
    private DaytimeController daytimeController;
    /**
     * Controller for ticket
     */
    private TicketController ticketController;
    /**
     * Controller for discount group
     */
    private DiscountGroupController discountGroupController;
    /**
     * Button to calculate the price
     */
    JButton confirmBtn = new JButton("Calculate price");

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public ReturnTicketView() {
        this.gridBagConstraints = new GridBagConstraints();
        this.watchController = new WatchController();
        this.ticketController = new TicketController();
        this.dayController = new DayController();
        this.daytimeController = new DaytimeController();
        discountGroupController = new DiscountGroupController();
        this.setLayout(new GridBagLayout());
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JLabel watchIdLabel = new JLabel("Watch id:");
        JTextField watchIdField = new JTextField();
        watchIdField.setPreferredSize(new Dimension(100, 30));

        ArrayList<Day> days = (ArrayList<Day>) dayController.getAllDays();
        Day[] days1 = days.toArray(new Day[days.size()]);
        JComboBox<Day> dayJComboBox = new JComboBox<Day>(days1);
        dayJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));
        dayJComboBox.setPreferredSize(new Dimension(250, 50));

        ArrayList<DiscountGroup> discountGroups = (ArrayList<DiscountGroup>) discountGroupController.getAllDiscountGroups();
        DiscountGroup[] discountGroups1 = discountGroups.toArray(new DiscountGroup[discountGroups.size()]);
        JComboBox<DiscountGroup> discountGroupJComboBox = new JComboBox<DiscountGroup>(discountGroups1);
        discountGroupJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));
        discountGroupJComboBox.setPreferredSize(new Dimension(250, 50));

        confirmBtn.setBackground(new Color(235, 127, 0));
        confirmBtn.setPreferredSize(new Dimension(100, 30));
        confirmBtn.setForeground(Color.white);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int watchId = Integer.parseInt(watchIdField.getText());
                Watch watch = watchController.getWatchById(watchId);

                if (watch == null) {
                    JOptionPane.showMessageDialog(null, "Watch with id: " + watchId + " does not exist!");
                } else {
                    Day day = (Day) dayJComboBox.getSelectedItem();
                    DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();
                    Ticket ticket = ticketController.findTicketForExit(watch);

//                    watch.setStatus(1);
//                    watchController.updateWatch(watch.getId(), 1);
                    ticket.setStampOut(new Date());
                    ticketController.updateTicket(ticket.getId(), ticket.getStamp(), ticket.getStampOut(), ticket.getWatch());
                    BigDecimal price = ticketController.calculatePrice(ticket, day, discountGroup);

                    JOptionPane.showMessageDialog(null, "Price to pay: " + price);
                }
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0 , 10, 0);

        gridBagConstraints.gridy = 0;
        this.add(watchIdLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(watchIdField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.add(discountGroupJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(dayJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(confirmBtn, gridBagConstraints);
    }
}
