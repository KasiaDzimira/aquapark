package View.Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Controller.*;
import Model.*;

/**
 * Cashier view for showing pass details
 */
public class PassDetailsView extends JFrame {
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * Controller for pass type
     */
    private PassTypeController passTypeController;
    /**
     * Controller for discount group
     */
    private DiscountGroupController discountGroupController;

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public PassDetailsView() {
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        passTypeController = new PassTypeController();
        discountGroupController = new DiscountGroupController();

        this.setBackground(Color.WHITE);
        this.prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        Calendar calendar = Calendar.getInstance();
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel passDetailsLabel = new JLabel("Specify a pass:");
        passDetailsLabel.setForeground(new Color(235, 127, 0));
        passDetailsLabel.setFont(new Font(passDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridLayout(4,3, 50 ,0));
        JLabel discountGroupLabel = new JLabel("Discount group:");
        JLabel passTypeLabel = new JLabel("Pass type:");

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


        JTextField costTextField = new JTextField();
        costTextField.setBackground(Color.WHITE);
        costTextField.setPreferredSize(new Dimension(50, 80));
        costTextField.setText("Cost:");


        ArrayList<DiscountGroup> discountGroups = (ArrayList<DiscountGroup>) discountGroupController.getAllDiscountGroups();
        DiscountGroup[] discountGroups1 = discountGroups.toArray(new DiscountGroup[discountGroups.size()]);
        JComboBox<DiscountGroup> discountGroupJComboBox = new JComboBox<DiscountGroup>(discountGroups1);
        discountGroupJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));

        ArrayList<PassType> passTypes = (ArrayList<PassType>) passTypeController.getAllPassTypes();
        PassType[] passTypes1 = passTypes.toArray(new PassType[passTypes.size()]);
        JComboBox<PassType> passTypeJComboBox = new JComboBox<PassType>(passTypes1);
        passTypeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton generateButton = new JButton("Create Pass");
        generateButton.setBackground(new Color(235, 127, 0));
        generateButton.setForeground(Color.WHITE);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();
                PassType passType = (PassType) passTypeJComboBox.getSelectedItem();
                Date dateFrom = (Date) fromSpinner.getValue();
                Date dateTo = (Date) toSpinner.getValue();

                createPass();
            }
        });


        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 40));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(800, 300));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(400, 50));

        passTypeJComboBox.setPreferredSize(new Dimension(180, 20));

        discountGroupJComboBox.setPreferredSize(new Dimension(180, 20));
        fromSpinner.setPreferredSize(new Dimension(50, 30));
        toSpinner.setPreferredSize(new Dimension(50, 30));
        generateButton.setPreferredSize(new Dimension(250, 40));

        detailsLabelPanel.add(passDetailsLabel);
        inputPanel.add(discountGroupLabel);
        inputPanel.add(passTypeLabel);

        inputPanel.add(discountGroupJComboBox);
        inputPanel.add(passTypeJComboBox);

        inputPanel.add(costTextField);

        inputPanel.add(fromLabel);
        inputPanel.add(toLabel);

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

    /**
     * Creates pass
     */
    private void createPass() { }


}
