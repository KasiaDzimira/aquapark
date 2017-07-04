package View.manager;

import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Manager view to create new position
 */
public class CreatePositionView extends JFrame {
    /**
     * Dimensions of the window
     */
    Dimension windowSize;
    /**
     * Constraints for GridBag layout manager
     */
    GridBagConstraints gridBagConstraints;
    /**
     * Controller for attraction type
     */
    AttractionTypeController attractionTypeController;
    /**
     * Controller for day
     */
    DayController dayController;
    /**
     * Controller for discount group
     */
    DiscountGroupController discountGroupController;
    /**
     * Controller for daytime
     */
    DaytimeController daytimeController;
    /**
     * Controller for ticket price list position
     */
    TicketPriceListPositionController ticketPriceListPositionController;
    /**
     * ComboBox with ticket price list positions
     */
    JComboBox<TicketPriceListPosition> ticketPriceListPositionJComboBox;
    /**
     * List with ticket prices
     */
    TicketPriceList ticketPriceList;

    /**
     * Constructor for CreatePositionView
     * Adjusts settings of elements to display
     * @param ticketPriceList List of ticket prices
     * @param ticketPriceListPositionJComboBox ComboBox with ticket prices
     */
    public CreatePositionView(TicketPriceList ticketPriceList, JComboBox<TicketPriceListPosition> ticketPriceListPositionJComboBox) {
        this.ticketPriceList = ticketPriceList;
        this.ticketPriceListPositionJComboBox = ticketPriceListPositionJComboBox;
        this.setTitle("Create position");
        this.gridBagConstraints = new GridBagConstraints();
        this.windowSize = new Dimension(500, 600);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        this.attractionTypeController = new AttractionTypeController();
        this.dayController = new DayController();
        this.discountGroupController = new DiscountGroupController();
        this.daytimeController = new DaytimeController();
        this.ticketPriceListPositionController = new TicketPriceListPositionController();
        this.centeringWindow();
        this.setSize(windowSize);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JLabel dayLabel = new JLabel("Day of week:");
        JLabel dayTimeLabel = new JLabel("Daytime");
        JLabel discountGroupLabel = new JLabel("Discount group:");
        JLabel attractionTypeLabel = new JLabel("Attraction type:");
        JLabel priceLabel = new JLabel("Price:");
        JButton createPositionButton = new JButton("Create");
        createPositionButton.setBackground(new Color(235, 127, 0));
        createPositionButton.setForeground(Color.WHITE);

        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
        JComboBox<AttractionType> attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        attractionTypeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));
        attractionTypeJComboBox.setPreferredSize(new Dimension(250, 50));

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

        JSpinner priceSpinner = new JSpinner();
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 0, 20,1);
        priceSpinner.setModel(spinnerNumberModel);
        priceSpinner.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        priceSpinner.setPreferredSize(new Dimension(250, 50));

        createPositionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Day day = (Day) dayJComboBox.getSelectedItem();
                Daytime daytime = (Daytime) daytimeJComboBox.getSelectedItem();
                DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();
                AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
                BigDecimal price = new BigDecimal((int) priceSpinner.getValue());
                TicketPriceListPosition ticketPriceListPosition = new TicketPriceListPosition(
                        price,
                        ticketPriceList,
                        day,
                        discountGroup,
                        daytime,
                        attractionType
                );

                ticketPriceListPositionController.createTicketPriceListPosition(
                        price,
                        ticketPriceList.getId(),
                        day.getId(),
                        discountGroup.getId(),
                        daytime.getId(),
                        attractionType.getId()
                );

                JOptionPane.showMessageDialog(null, "Position has been successfully added!");
                ticketPriceListPositionJComboBox.addItem(ticketPriceListPosition);
                dispose();
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        gridBagConstraints.gridy = 0;
        this.add(dayLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(dayJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.add(dayTimeLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(daytimeJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(discountGroupLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        this.add(discountGroupJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        this.add(attractionTypeLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        this.add(attractionTypeJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        this.add(priceLabel, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        this.add(priceSpinner, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        this.add(createPositionButton, gridBagConstraints);
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
