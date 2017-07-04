package View.Cashier;

import Controller.DiscountGroupController;
import Controller.PassController;
import Controller.PassTypeController;
import Controller.UserController;
import Dictionary.UserRoleDictionary;
import Model.DiscountGroup;
import Model.PassType;
import Model.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Cashier view to create pass for new client
 */
public class CreatePassForNewClientView extends JFrame {
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
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
     * Chooser for starting date
     */
    private JDateChooser dateChooserFrom = new JDateChooser();
    /**
     * Chooser for ending date
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
     * Button to confirm
     */
    JButton confirmBtn = new JButton("Confirm");

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public CreatePassForNewClientView() {
        this.gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.windowSize = new Dimension(700, 700);
        this.setSize(this.windowSize);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        this.userController = new UserController();
        this.discountGroupController = new DiscountGroupController();
        this.passController = new PassController();
        this.passTypeController = new PassTypeController();
        this.centeringWindow();
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Create client and pass");
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JLabel selectedUserLabel = new JLabel("Add new client and pass:");
        selectedUserLabel.setForeground(new Color(235, 127, 0));
        selectedUserLabel.setFont(new Font(selectedUserLabel.getFont().getName(), Font.PLAIN, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(300, 500));

        JLabel nickLabel = new JLabel("Nick:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel firstNameLabel = new JLabel("First name:");
        JLabel lastNameLabel = new JLabel("Last name:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel telephoneLabel = new JLabel("Telephone:");
        JTextField nickField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telephoneField = new JTextField();

        Dimension fieldSize = new Dimension(300, 30);
        firstNameField.setPreferredSize(fieldSize);
        lastNameField.setPreferredSize(fieldSize);
        emailField.setPreferredSize(fieldSize);
        telephoneField.setPreferredSize(fieldSize);
        nickField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        formPanel.add(nickLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        formPanel.add(nickField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        formPanel.add(passwordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        formPanel.add(passwordField, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        formPanel.add(firstNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        formPanel.add(firstNameField, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        formPanel.add(lastNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        formPanel.add(lastNameField, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        formPanel.add(emailLabel, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        formPanel.add(emailField, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        formPanel.add(telephoneLabel, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        formPanel.add(telephoneField, gridBagConstraints);

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

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(300, 500));

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

        dateChooserFrom.setPreferredSize(new Dimension(200, 20));
        dateChooserTo.setPreferredSize(new Dimension(200, 20));

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController userController = new UserController();
                String nick = nickField.getText();
                boolean accountExists = userController.checkIfAccountExist(nick);
                if (accountExists) {
                    JOptionPane.showMessageDialog(null, "Account with nick: " + nick + " already exists!");
                }

                if (!accountExists) {
                    String password = passwordField.getText();
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = emailField.getText();
                    String telephone = telephoneField.getText();
                    userController.createClient(nick, password, firstName, lastName, email, telephone, UserRoleDictionary.ROLE_USER);
                    ArrayList<User> users = userController.findBy("nick", nick);
                    User user = users.get(0);
                    Date startDate = dateChooserFrom.getDate();
                    Date endDate = dateChooserTo.getDate();
                    DiscountGroup discountGroup = (DiscountGroup) discountGroupJComboBox.getSelectedItem();
                    PassType passType = (PassType) passTypeJComboBox.getSelectedItem();

                    passController.createPass(startDate, endDate, user, passType, discountGroup);
                    BigDecimal price = passController.calculatePrice(passType, startDate, endDate, discountGroup, false);

                    JOptionPane.showMessageDialog(null,
                            "Pass for user: " + user.getFirstName() + " " + user.getLastName() + " has been created!\n" +
                                    "Pass price: " + price
                    );

                    dispose();
                }
            }
        });

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 15, 20);
        this.add(selectedUserLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(formPanel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
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
