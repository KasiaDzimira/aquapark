package View.Cashier;

import Controller.PassController;
import Model.Pass;
import Model.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Cashier view for extending the pass
 */
public class ExtendPassView extends JFrame {
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * Selected user
     */
    private User selectedUser;
    /**
     * Controller for pass
     */
    private PassController passController;
    /**
     * ArrayList of passes
     */
    private ArrayList<Pass> passes;
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * List of passes
     */
    private JList<Pass> passJList;
    /**
     * Default list
     */
    private DefaultListModel defaultListModel;
    /**
     * Scroll pane
     */
    private JScrollPane pane;
    /**
     * Label - Since
     */
    private JLabel sinceLabel = new JLabel("Since:");
    /**
     * LAbel - Until
     */
    private JLabel untilLabel = new JLabel("Until:");
    /**
     * Label - Pass type
     */
    private JLabel passTypeLabel = new JLabel("Pass type:");
    /**
     * Label
     */
    private JLabel passType = new JLabel("");
    /**
     * Chooser for pass type
     */
    private JDateChooser dateChooserFrom = new JDateChooser();
    /**
     * Chooser for ending date
     */
    private JDateChooser dateChooserTo = new JDateChooser();
    /**
     * Label - Discount group
     */
    private JLabel discountGroupLabel = new JLabel("Discount group:");
    /**
     * Label for discount group
     */
    private JLabel discountGroup = new JLabel("");
    /**
     * Selected pass
     */
    private Pass selectedPass;
    /**
     * Button to confirm
     */
    JButton confirmBtn = new JButton("Confirm");

    /**
     * Constructor for ExtendPassView
     * Sets elements to display
     * @param selectedUser Chosen user to extend pass of
     */
    public ExtendPassView(User selectedUser) {
        this.gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        defaultListModel = new DefaultListModel();
        pane = new JScrollPane();
        this.passJList = new JList<>(defaultListModel);
        this.windowSize = new Dimension(700, 700);
        this.setSize(this.windowSize);
        this.centeringWindow();
        this.setLayout(new GridBagLayout());
        this.selectedUser = selectedUser;
        this.passController = new PassController();
        passes = (ArrayList<Pass>) passController.findPassesForUser(selectedUser.getId());
        this.setVisible(true);
        this.getContentPane().setBackground(Color.WHITE);
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    public void prepareGui() {
        JLabel selectedUserLabel = new JLabel("Selected user:");
        JLabel userName = new JLabel(selectedUser.getFirstName() + ' ' + selectedUser.getLastName());

        confirmBtn.setBackground(new Color(235, 127, 0));
        confirmBtn.setPreferredSize(new Dimension(100, 30));
        confirmBtn.setForeground(Color.white);

        setOptionsVisibility(false);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(350, 500));

        dateChooserFrom.setPreferredSize(new Dimension(200, 20));
        dateChooserTo.setPreferredSize(new Dimension(200, 20));

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 15);
        inputPanel.add(passTypeLabel, gridBagConstraints);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        inputPanel.add(passType, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        inputPanel.add(discountGroupLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        inputPanel.add(discountGroup, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        inputPanel.add(sinceLabel, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 1;
        inputPanel.add(dateChooserFrom, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        inputPanel.add(untilLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 1;
        inputPanel.add(dateChooserTo, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        inputPanel.add(confirmBtn, gridBagConstraints);

        selectedUserLabel.setForeground(new Color(235, 127, 0));
        selectedUserLabel.setFont(new Font(selectedUserLabel.getFont().getName(), Font.PLAIN, 30));

        userName.setFont(new Font(userName.getFont().getName(), Font.PLAIN, 20));

        prepareList();
        pane.setViewportView(passJList);
        pane.setPreferredSize(new Dimension(200, 300));

        passJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();

                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        selectedPass = (Pass) defaultListModel.get(selections[0]);
                        dateChooserFrom.setDate(selectedPass.getStartDate());
                        dateChooserTo.setDate(selectedPass.getEndDate());
                        passType.setText(selectedPass.getPassType().toString());
                        discountGroup.setText(selectedPass.getDiscountGroup().toString());
                        setOptionsVisibility(true);
                    }
                }
            }
        });

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = dateChooserFrom.getDate();
                Date endDate = dateChooserTo.getDate();

                if (startDate != selectedPass.getStartDate() && (startDate.after(selectedPass.getEndDate()) || startDate == selectedPass.getEndDate())) {
                    if (endDate.after(startDate) && endDate != startDate) {
                        BigDecimal price = passController.calculatePrice(selectedPass.getPassType(), startDate, endDate, selectedPass.getDiscountGroup(), false);
                        selectedPass.setStartDate(startDate);
                        selectedPass.setEndDate(endDate);
                        passController.updatePass(selectedPass.getId(), startDate, endDate, selectedPass.getUser(), selectedPass.getPassType(), selectedPass.getDiscountGroup());
                        JOptionPane.showMessageDialog(null, "Price of the pass:" + price);
                    }
                } else if (startDate.equals(selectedPass.getStartDate()) && endDate.after(selectedPass.getEndDate())) {
                    startDate = selectedPass.getEndDate();
                    selectedPass.setEndDate(endDate);
                    BigDecimal price = passController.calculatePrice(selectedPass.getPassType(), startDate, endDate, selectedPass.getDiscountGroup(), true);
                    passController.updatePass(selectedPass.getId(), selectedPass.getStartDate(), endDate, selectedPass.getUser(), selectedPass.getPassType(), selectedPass.getDiscountGroup());
                    JOptionPane.showMessageDialog(null, "Price of the pass:" + price);
                } else {
                    JOptionPane.showMessageDialog(null, "Selected time interval is incorrect! Try again.");
                }
            }
        });

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        this.add(selectedUserLabel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.add(userName, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        this.add(pane, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        this.add(inputPanel, gridBagConstraints);
    }

    /**
     * Prepares list of available passes
     */
    private void prepareList() {
        passJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defaultListModel.removeAllElements();

        for (Pass pass : passes) {
            defaultListModel.addElement(pass);
        }
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

    /**
     * Shows information fields
     * @param visible State of fields
     */
    private void setOptionsVisibility(boolean visible) {
        sinceLabel.setVisible(visible);
        untilLabel.setVisible(visible);
        passTypeLabel.setVisible(visible);
        passType.setVisible(visible);
        dateChooserFrom.setVisible(visible);
        dateChooserTo.setVisible(visible);
        confirmBtn.setVisible(visible);
        discountGroupLabel.setVisible(visible);
        discountGroup.setVisible(visible);
    }
}
