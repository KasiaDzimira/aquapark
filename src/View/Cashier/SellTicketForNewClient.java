package View.Cashier;

import Controller.*;
import Model.Day;
import Model.Daytime;
import Model.DiscountGroup;
import Model.Watch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Cashier view for selling ticket to new client
 */
public class SellTicketForNewClient extends JFrame {
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * Contrller for watch
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
     * ComboBox with discount groups
     */
    private JComboBox<DiscountGroup> discountGroupJComboBox;
    /**
     * Controller for discount group
     */
    private DiscountGroupController discountGroupController;
    /**
     * Button to calculate price
     */
    JButton confirmBtn = new JButton("Calculate price");

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public SellTicketForNewClient() {
        this.windowSize = new Dimension(300, 300);
        this.gridBagConstraints = new GridBagConstraints();
        this.watchController = new WatchController();
        this.ticketController = new TicketController();
        this.dayController = new DayController();
        this.daytimeController = new DaytimeController();
        this.setSize(this.windowSize);
        discountGroupController = new DiscountGroupController();
        this.setVisible(true);
        centeringWindow();
        this.setLayout(new GridBagLayout());
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
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

        ArrayList<Daytime> daytimes = (ArrayList<Daytime>) daytimeController.getAllDaytimes();
        Daytime[] daytimes1 = daytimes.toArray(new Daytime[daytimes.size()]);
        JComboBox<Daytime> daytimeJComboBox = new JComboBox<Daytime>(daytimes1);
        daytimeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));
        daytimeJComboBox.setPreferredSize(new Dimension(250, 50));

        confirmBtn.setBackground(new Color(235, 127, 0));
        confirmBtn.setPreferredSize(new Dimension(100, 30));
        confirmBtn.setForeground(Color.white);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentDate = new Date();
                Watch watch = watchController.getFreeWatch();
                watch.setStatus(2);
                watchController.updateWatch(watch.getId(), 2);
                ticketController.createTicket(currentDate, watch, null);
                Day day = (Day) dayJComboBox.getSelectedItem();
                Daytime daytime = (Daytime) daytimeJComboBox.getSelectedItem();
                DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();

                BigDecimal price = ticketController.calculateBasePrice(day, discountGroup, daytime);

                JOptionPane.showMessageDialog(null, "Price to pay: " + price);
                dispose();
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0 , 10, 0);

        gridBagConstraints.gridy = 0;
        this.add(discountGroupJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(dayJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.add(daytimeJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(confirmBtn, gridBagConstraints);
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
