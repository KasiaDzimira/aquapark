package View.AdminPanel;

import Controller.*;
import Model.*;
import javafx.scene.control.ComboBox;
import org.w3c.dom.Attr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StatisticsView extends JPanel {
    private GridBagConstraints gridBagConstraints;
    private AttractionTypeController attractionTypeController;
    private DiscountGroupController discountGroupController;
    private AttractionController attractionController;
    private JComboBox<Attraction> attractionJComboBox;
    private JComboBox<AttractionType> attractionTypeJComboBox;

    public StatisticsView() {
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        attractionTypeController = new AttractionTypeController();
        discountGroupController = new DiscountGroupController();
        attractionController = new AttractionController();
        attractionTypeJComboBox = this.createAttractionTypeJComboBox();
        attractionJComboBox = this.updateAttractionJComboBox((AttractionType) attractionTypeJComboBox.getSelectedItem());
        this.setBackground(Color.WHITE);
        this.prepareGui();
    }

    private void prepareGui() {
        Calendar calendar = Calendar.getInstance();
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Select report parameters:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridLayout(4,3, 50 ,0));
        JLabel discountGroupLabel = new JLabel("Discount group:");
        JLabel attractionTypeLabel = new JLabel("Attraction type:");
        JLabel attractionLabel = new JLabel("Attraction:");
        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerModel fromDateModel = new SpinnerDateModel(initDate,
                earliestDate,
                latestDate,
                Calendar.YEAR);
        SpinnerModel toDateModel = new SpinnerDateModel(initDate,
                earliestDate,
                latestDate,
                Calendar.YEAR);
        JSpinner fromSpinner = new JSpinner();
        fromSpinner.setModel(fromDateModel);
        fromSpinner.setEditor(new JSpinner.DateEditor(fromSpinner, "dd/MM/yyyy"));
        fromSpinner.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        JSpinner toSpinner = new JSpinner();
        toSpinner.setModel(toDateModel);
        toSpinner.setEditor(new JSpinner.DateEditor(toSpinner, "dd/MM/yyyy"));
        toSpinner.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        JRadioButton isTicketRadio = new JRadioButton();
        isTicketRadio.setBackground(Color.WHITE);
        isTicketRadio.setPreferredSize(new Dimension(50, 80));
        isTicketRadio.setText("Ticket:");
        isTicketRadio.setHorizontalTextPosition(JRadioButton.LEFT);

        JRadioButton isPassRadio = new JRadioButton();
        isPassRadio.setBackground(Color.WHITE);
        isPassRadio.setPreferredSize(new Dimension(50, 80));
        isPassRadio.setText("Pass:");
        isPassRadio.setHorizontalTextPosition(JRadioButton.LEFT);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(isTicketRadio);
        buttonGroup.add(isPassRadio);

        ArrayList<DiscountGroup> discountGroups = (ArrayList<DiscountGroup>) discountGroupController.getAllDiscountGroups();
        DiscountGroup[] discountGroups1 = discountGroups.toArray(new DiscountGroup[discountGroups.size()]);
        JComboBox<DiscountGroup> discountGroupJComboBox = new JComboBox<DiscountGroup>(discountGroups1);
        discountGroupJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton generateButton = new JButton("Generate report");
        generateButton.setBackground(new Color(235, 127, 0));
        generateButton.setForeground(Color.WHITE);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();
                AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
                Attraction attraction = (Attraction) attractionJComboBox.getSelectedItem();
                Boolean isTicket = isTicketRadio.isSelected();
                Boolean isPass = isPassRadio.isSelected();
                Date dateFrom = (Date) fromSpinner.getValue();
                Date dateTo = (Date) toSpinner.getValue();

                generateStatistics(discountGroup, attractionType, attraction, dateFrom, dateTo, isTicket, isPass);
            }
        });

        attractionTypeJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    attractionJComboBox = updateAttractionJComboBox((AttractionType) e.getItem());
                    attractionJComboBox.revalidate();
                    attractionJComboBox.repaint();
                }
            }
        });

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 40));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(800, 300));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(400, 50));

        attractionTypeJComboBox.setPreferredSize(new Dimension(180, 20));
        attractionJComboBox.setPreferredSize(new Dimension(180, 20));
        discountGroupJComboBox.setPreferredSize(new Dimension(180, 20));
        fromSpinner.setPreferredSize(new Dimension(50, 30));
        toSpinner.setPreferredSize(new Dimension(50, 30));
        generateButton.setPreferredSize(new Dimension(250, 40));

        detailsLabelPanel.add(attractionDetailsLabel);
        inputPanel.add(discountGroupLabel);
        inputPanel.add(attractionTypeLabel);
        inputPanel.add(attractionLabel);
//        inputPanel.add(discountGroupJComboBox);
        inputPanel.add(attractionTypeJComboBox);
        inputPanel.add(attractionJComboBox);
        inputPanel.add(isTicketRadio);
        inputPanel.add(fromLabel);
        inputPanel.add(toLabel);
        inputPanel.add(isPassRadio);
        inputPanel.add(fromSpinner);
        inputPanel.add(toSpinner);

        GridBagConstraints gridBagConstraintsBtn = new GridBagConstraints();
        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        buttonPanel.add(generateButton, gridBagConstraintsBtn);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridy = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(new JSeparator(), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 50, 0);
        this.add(buttonPanel, gridBagConstraints);
        this.setVisible(true);
    }

    private void generateStatistics(DiscountGroup discountGroup, AttractionType attractionType,
                                    Attraction attraction, Date dateFrom, Date dateTo,
                                    Boolean isTicket, Boolean isPass) {
        HistoryController historyController = new HistoryController();
        ArrayList<History> histories = new ArrayList<>();
        if (attractionType == null) {
            histories = (ArrayList<History>) historyController.findAllByDateFromAndDateTo(dateFrom, dateTo);
        } else {
            AttractionController attractionController = new AttractionController();
            if (attraction == null) {
                for (Attraction a : attractionController.getAttractionByType(attractionType)) {
                    histories.addAll(historyController.findAllByAttractionAndDateFromAndDateTo(a, dateFrom, dateTo));
                }
            } else {
                histories = (ArrayList<History>) historyController.findAllByAttractionAndDateFromAndDateTo(attraction, dateFrom, dateTo);
            }
        }
        String message = "";
        if (!histories.isEmpty()) {
            if (isTicket) {
                message += getHistoryAndTicketInfo(histories);
            }
            if (isPass) {
                message += getHistoryAndPassInfo(histories);
            }
        }
        if (message.isEmpty()) {
            message = "There is no entries matching Your requirements.";
        }
        Object[] options = {"Close", "Export to PDF", "Print"};
        showOptionBox(message, options);

    }

    private int showOptionBox(String message, Object[] options) {
        JTextArea textArea = new JTextArea(message);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 500, 500) );
        return JOptionPane.showOptionDialog(null, scrollPane, "Have a nice day :)",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
    }

    private String getHistoryAndPassInfo(ArrayList<History> histories) {
        TicketController ticketController = new TicketController();
        String info = "";
        for (History h : histories) {
            Ticket ticket = ticketController.findByWatchAndDatesWithinStamps(h.getWatch(), h.getEntryTime(), h.getExitTime());
            if (ticket != null) {
                Pass pass = ticket.getPass();
                if (pass != null) {
                    info += "Using pass by: " + pass.getUser() + "\n" + "\tEntry time: " + h.getEntryTime() + " \n\tExit time: " + h.getExitTime() +
                            "\n\ton Attraction: " + h.getAttraction().getName() + "\n";
                }
            }
        }
        return info;

    }

    private String getHistoryAndTicketInfo(ArrayList<History> histories) {
        TicketController ticketController = new TicketController();

        String info = "";
        for (History h : histories) {
            Ticket ticket = ticketController.findByWatchAndDatesWithinStamps(h.getWatch(), h.getEntryTime(), h.getExitTime());
            if (ticket != null && ticket.getPass() == null) {
                info += "Using ticket:\n" + "\tEntry time: " + h.getEntryTime() + " \n\tExit time: " + h.getExitTime() +
                        "\n\ton Attraction: " + h.getAttraction().getName() + "\n";
            }
        }
        return info;
    }

    protected JComboBox<Attraction> updateAttractionJComboBox(AttractionType attractionType) {
        ArrayList<Attraction> attractions = (ArrayList<Attraction>) attractionController.getAttractionByType(attractionType);
        Attraction[] attractions1 = attractions.toArray(new Attraction[attractions.size()]);
        if (attractionJComboBox != null) {
            attractionJComboBox.setModel(new DefaultComboBoxModel<>(attractions1));
        } else {
            attractionJComboBox = new JComboBox<Attraction>(attractions1);
        }
        attractionJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));

        return  attractionJComboBox;
    }

    protected JComboBox<AttractionType> createAttractionTypeJComboBox() {
        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
        JComboBox<AttractionType> attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        attractionTypeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));

        return attractionTypeJComboBox;
    }
}
