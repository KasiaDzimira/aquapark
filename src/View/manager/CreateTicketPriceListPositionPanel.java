package View.manager;

import Controller.*;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Manager view to create ticket price position
 */
public class CreateTicketPriceListPositionPanel extends JPanel {
    /**
     * List with attractions
     */
    private JList<Attraction> attractionList;
    /**
     * List with attractions types
     */
    private JList<AttractionType> attractionTypeList;
    /**
     * Default list for attractions
     */
    private DefaultListModel attractionListModel;
    /**
     * Default list for attractions types
     */
    private DefaultListModel attractionTypeListModel;
    /**
     * Panel with options
     */
    private JPanel options;
    /**
     * ComboBox with days
     */
    private JComboBox<Day> dayJComboBox;
    /**
     * ComboBox with daytime
     */
    private JComboBox<Daytime> daytimeJComboBox;
    /**
     * ComboBox with discount groups
     */
    private JComboBox<DiscountGroup> discountGroupJComboBox;
    /**
     * Field with price
     */
    private JTextField priceField;
    /**
     * Controller for day
     */
    private DayController dayController;
    /**
     * Controller for daytime
     */
    private DaytimeController daytimeController;
    /**
     * Controller for discount group
     */
    private DiscountGroupController discountGroupController;
    /**
     * Controller for ticket price list position
     */
    private TicketPriceListPositionController ticketPriceListPositionController;
    /**
     * List with ticket prices
     */
    private TicketPriceList ticketPriceList;
    /**
     * ArrayList with all attractions
     */
    private ArrayList<Attraction> allAttraction;
    /**
     * Controller for attraction type
     */
    AttractionTypeController attractionTypeController;
    /**
     * Controller for attraction
     */
    AttractionController attractionController;

    /**
     * Constructor for CreateTicketPriceListPositionPanel
     * Adjusts settings of elements to display
     * @param ticketPriceList List of ticket prices
     */
    public CreateTicketPriceListPositionPanel(TicketPriceList ticketPriceList) {
        this.ticketPriceList = ticketPriceList;
        attractionTypeController = new AttractionTypeController();
        attractionController = new AttractionController();
        allAttraction = new ArrayList<Attraction>();
        dayController = new DayController();
        daytimeController = new DaytimeController();
        discountGroupController = new DiscountGroupController();
        ticketPriceListPositionController = new TicketPriceListPositionController();
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        this.setLayout(new FlowLayout());
        options = new JPanel();
        prepareOptions();
        prepareLists();
        options.setVisible(false);
        this.add(attractionTypeList);
        this.add(attractionList);
        this.add(options);
        this.setVisible(true);
    }

    /**
     * Prepares lists to show
     */
    private void prepareLists() {
        attractionTypeListModel = new DefaultListModel();
        attractionListModel = new DefaultListModel();

        //load data type
//        mockUp();
        loadFromDB();

        attractionTypeList = new JList<>(attractionTypeListModel);
        attractionList = new JList<>(attractionListModel);
        attractionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //adding listeners
        attractionTypeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    filterAttractions(selections);
                    attractionList.clearSelection();
                    options.setVisible(false);
                }
            }
        });

        attractionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        Attraction attraction = (Attraction) attractionListModel.get(selections[0]);
                        setOptions(attraction);
                        options.setVisible(true);
                        options.revalidate();
                        options.repaint();
                    }
                }
            }
        });
    }

    /**
     * Sets available options
     * @param attraction Attraction
     */
    private void setOptions(Attraction attraction) {
    }

    /**
     * Filters attractions
     * @param selectedAttractionTypes Selected types of attractions
     */
    private void filterAttractions(int[] selectedAttractionTypes) {
        Set<AttractionType> attractionTypeSet = new HashSet<>();
        for (int i = 0; i < selectedAttractionTypes.length; i++) {
            attractionTypeSet.add((AttractionType) attractionTypeListModel.get(selectedAttractionTypes[i]));
        }
//        attractionListModel = new DefaultListModel();
        attractionList.clearSelection();
        attractionListModel.removeAllElements();

        for (Attraction a : allAttraction) {
            for (AttractionType at : attractionTypeSet) {
                if (at.getId() == a.getAttractionType().getId()) {
                    attractionListModel.addElement(a);
                }
            }
        }
    }

    /**
     * Loads attraction types from database
     */
    public void loadFromDB() {
        for (AttractionType at : attractionTypeController.getAllAttractionTypes()) {
            attractionTypeListModel.addElement(at);
        }
        for (Attraction a : attractionController.getAllAttractions()) {
            allAttraction.add(a);
            attractionListModel.addElement(a);
        }
    }

    /**
     * Refreshes lists
     */
    private void refreshLists() {
        attractionList.revalidate();
        attractionList.repaint();
        attractionTypeList.revalidate();
        attractionTypeList.repaint();
    }

    /**
     * Prepares available options
     */
    private void prepareOptions() {
        BoxLayout boxLayout = new BoxLayout(options, BoxLayout.Y_AXIS);
        options.setLayout(boxLayout);
        ArrayList<Day> dayArrayList = (ArrayList<Day>) dayController.getAllDays();
        System.out.println(dayArrayList);
        ArrayList<Daytime> daytimeArrayList = (ArrayList<Daytime>) daytimeController.getAllDaytimes();
        ArrayList<DiscountGroup> discountGroupArrayList = (ArrayList<DiscountGroup>) discountGroupController.getAllDiscountGroups();
        Day[] days = dayArrayList.toArray(new Day[dayArrayList.size()]);
        Daytime[] daytimes = daytimeArrayList.toArray(new Daytime[daytimeArrayList.size()]);
        DiscountGroup[] discountGroups = discountGroupArrayList.toArray(new DiscountGroup[discountGroupArrayList.size()]);

        dayJComboBox = new JComboBox<Day>(days);
        daytimeJComboBox = new JComboBox<Daytime>(daytimes);
        discountGroupJComboBox = new JComboBox<DiscountGroup>(discountGroups);
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndCreate();
            }
        });

        priceField = new JTextField("price");
        options.add(priceField);
        options.add(dayJComboBox);
        options.add(daytimeJComboBox);
        options.add(discountGroupJComboBox);
        options.add(createButton);
    }

    /**
     * Validates position to create
     */
    private void validateAndCreate() {
        //TODO: validation

        Day day = dayJComboBox.getItemAt(dayJComboBox.getSelectedIndex());
        Daytime daytime = daytimeJComboBox.getItemAt(dayJComboBox.getSelectedIndex());
        DiscountGroup discountGroup = discountGroupJComboBox.getItemAt(discountGroupJComboBox.getSelectedIndex());
        int price = Integer.parseInt(priceField.getText());
        Attraction attraction = attractionList.getSelectedValue();

        if (day != null && daytime != null && discountGroup!= null && attraction != null) {
//            ticketPriceListPositionController.createTicketPriceListPosition(price, ticketPriceList.getId(), day.getId(), discountGroup.getId(), daytime.getId(), attraction.getId());
        }
    }
}
