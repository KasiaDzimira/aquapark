package View.Cashier;

import Controller.DiscountGroupController;
import Controller.PassController;
import Controller.PassTypeController;
import Controller.UserController;
import Model.*;
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
 * Cashier view to create pass for existing client
 */
public class CreatePassForExistingClientView extends JFrame {
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * List of users
     */
    private JList<User> userJList;
    /**
     * Default list
     */
    private DefaultListModel defaultListModel;
    /**
     * Controller for user
     */
    private UserController userController;
    /**
     * Controller for discount group
     */
    private DiscountGroupController discountGroupController;
    /**
     * Controller for pass type
     */
    private PassTypeController passTypeController;
    /**
     * Controller for pass
     */
    private PassController passController;
    /**
     * Scroll pane
     */
    private JScrollPane pane;
    /**
     * Label - Since
     */
    private JLabel sinceLabel = new JLabel("Since:");
    /**
     * Label - Until
     */
    private JLabel untilLabel = new JLabel("Until:");
    /**
     * Label - Pass type
     */
    private JLabel passTypeLabel = new JLabel("Pass type:");
    /**
     * Label - Discount group
     */
    private JLabel discountGroupLabel = new JLabel("Discount group:");
    /**
     * Date chooser for starting date
     */
    private JDateChooser dateChooserFrom = new JDateChooser();
    /**
     * Date chooser for ending date
     */
    private JDateChooser dateChooserTo = new JDateChooser();
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * ComboBox with discount groups
     */
    private JComboBox<DiscountGroup> discountGroupJComboBox;
    /**
     * ComboBox with pass types
     */
    private JComboBox<PassType> passTypeJComboBox;
    /**
     * Selected user
     */
    private User selectedUser;
    /**
     * Button to confirm
     */
    JButton confirmBtn = new JButton("Confirm");

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public CreatePassForExistingClientView() {
        this.gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.windowSize = new Dimension(700, 700);
        this.setSize(this.windowSize);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        this.defaultListModel = new DefaultListModel();
        this.userJList = new JList<>(this.defaultListModel);
        this.userController = new UserController();
        this.discountGroupController = new DiscountGroupController();
        this.passController = new PassController();
        this.passTypeController = new PassTypeController();
        this.pane = new JScrollPane();
        this.centeringWindow();
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Create pass for existing user");
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JLabel selectedUserLabel = new JLabel("Select user:");
        selectedUserLabel.setForeground(new Color(235, 127, 0));
        selectedUserLabel.setFont(new Font(selectedUserLabel.getFont().getName(), Font.PLAIN, 20));

        ArrayList<DiscountGroup> discountGroups = (ArrayList<DiscountGroup>) discountGroupController.getAllDiscountGroups();
        DiscountGroup[] discountGroups1 = discountGroups.toArray(new DiscountGroup[discountGroups.size()]);
        discountGroupJComboBox = new JComboBox<DiscountGroup>(discountGroups1);
        discountGroupJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));
        discountGroupJComboBox.setPreferredSize(new Dimension(200, 50));

        ArrayList<PassType> passTypes = (ArrayList<PassType>) passTypeController.getAllPassTypes();
        PassType[] passTypes1 = passTypes.toArray(new PassType[passTypes.size()]);
        passTypeJComboBox = new JComboBox<PassType>(passTypes1);
        passTypeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));
        passTypeJComboBox.setPreferredSize(new Dimension(200, 50));

        confirmBtn.setBackground(new Color(235, 127, 0));
        confirmBtn.setPreferredSize(new Dimension(100, 30));
        confirmBtn.setForeground(Color.white);

        userJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();

                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        selectedUser = (User) defaultListModel.get(selections[0]);
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
                DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();
                PassType passType = (PassType) passTypeJComboBox.getSelectedItem();

                passController.createPass(startDate, endDate, selectedUser, passType, discountGroup);
                BigDecimal price = passController.calculatePrice(passType, startDate, endDate, discountGroup, false);

                JOptionPane.showMessageDialog(null,
                        "Pass for user: " + selectedUser.getFirstName() + " " + selectedUser.getLastName() + " has been created!\n" +
                                "Price: " + price
                );
            }
        });

        setOptionsVisibility(false);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(350, 500));

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 15, 0);
        inputPanel.add(passTypeLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        inputPanel.add(passTypeJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        inputPanel.add(discountGroupLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        inputPanel.add(discountGroupJComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        inputPanel.add(sinceLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        inputPanel.add(dateChooserFrom, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        inputPanel.add(untilLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        inputPanel.add(dateChooserTo, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        inputPanel.add(confirmBtn, gridBagConstraints);

        dateChooserFrom.setPreferredSize(new Dimension(200, 20));
        dateChooserTo.setPreferredSize(new Dimension(200, 20));

        prepareList();
        pane.setViewportView(userJList);
        pane.setPreferredSize(new Dimension(200, 400));

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 15, 20);
        this.add(selectedUserLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(pane, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.add(inputPanel, gridBagConstraints);
    }

    /**
     * Prepares list of users
     */
    private void prepareList() {
        defaultListModel.removeAllElements();

        for (User user : userController.getUsers()) {
            defaultListModel.addElement(user);
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
        dateChooserFrom.setVisible(visible);
        dateChooserTo.setVisible(visible);
        confirmBtn.setVisible(visible);
        discountGroupLabel.setVisible(visible);
        discountGroupJComboBox.setVisible(visible);
        passTypeJComboBox.setVisible(visible);
    }
}
