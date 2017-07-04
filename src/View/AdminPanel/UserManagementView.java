package View.AdminPanel;

import Controller.UserController;
import Dictionary.UserRoleDictionary;
import Model.User;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Admin view for managing users
 */
public class UserManagementView extends JPanel {
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * List with users
     */
    private JList<User> userList;
    /**
     * Default user list
     */
    private DefaultListModel userListModel;
    /**
     * Controller for user
     */
    private UserController userController;
    /**
     * Label with nick
     */
    private JLabel nickLabel;
    /**
     * Label with first name
     */
    private JLabel firstNameLabel;
    /**
     * Label with last name
     */
    private JLabel lastNameLabel;
    /**
     * Label with role
     */
    private JLabel userRoleLabel;
    /**
     * Field with nick
     */
    private JTextField nickField;
    /**
     * Field with first name
     */
    private JTextField firstNameField;
    /**
     * Field with last name
     */
    private JTextField lastNameField;
    /**
     * ComboBox with user roles
     */
    private JComboBox userRoleComboBox;
    /**
     * Scroll pane
     */
    private JScrollPane pane = new JScrollPane();

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public UserManagementView() {
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        userController = new UserController();
        userListModel = new DefaultListModel();
        userList = new JList<>(userListModel);
        userList.setFixedCellHeight(50);
        pane.setViewportView(userList);
        nickLabel = new JLabel("Nick:");
        firstNameLabel = new JLabel("First name:");
        lastNameLabel = new JLabel("Last name:");
        userRoleLabel = new JLabel("User role:");
        nickField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();

        String[] userRoles = {
                UserRoleDictionary.ROLE_USER.toString(),
                UserRoleDictionary.ROLE_MANAGER.toString(),
                UserRoleDictionary.ROLE_ADMIN.toString(),
                UserRoleDictionary.ROLE_EMPLOYEE.toString()
        };
        userRoleComboBox = new JComboBox(userRoles);
        userRoleComboBox.setSelectedIndex(0);
        this.setBackground(Color.WHITE);
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Select user:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridBagLayout());

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        updateButton.setBackground(new Color(235, 127, 0));
        updateButton.setForeground(Color.WHITE);
        removeButton.setBackground(new Color(235, 127, 0));
        removeButton.setForeground(Color.WHITE);

        nickField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        firstNameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        lastNameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        userRoleComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        setOptionsVisibility(false);
        prepareList();
        refreshLists();

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(700, 80));

        Dimension panelSize = getSize();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(300, 700));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(300, 60));

        nickField.setPreferredSize(new Dimension(200, 50));
        firstNameField.setPreferredSize(new Dimension(200, 50));
        lastNameField.setPreferredSize(new Dimension(200, 50));
        userRoleComboBox.setPreferredSize(new Dimension(200, 50));
        updateButton.setPreferredSize(new Dimension(120, 50));
        removeButton.setPreferredSize(new Dimension(120, 50));
        userList.setSize(new Dimension(300, 600));

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = nickField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String userRole = (String) userRoleComboBox.getSelectedItem();
                User user = userList.getSelectedValue();

                userController.updateUser(user.getId(), nick, firstName, lastName, userRole);
                JOptionPane.showMessageDialog(null, "User has been successfully updated!");

                setOptionsVisibility(false);
                userList.getSelectedValue().setRole(userController.prepareUserRole(userRole));
                userList.getSelectedValue().setNick(nick);
                userList.getSelectedValue().setFirstName(firstName);
                userList.getSelectedValue().setLastName(lastName);
                userList.clearSelection();
                refreshLists();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = userList.getSelectedValue();

                userController.deleteUser(user);
                JOptionPane.showMessageDialog(null, "User has been succesfully removed!");

                userListModel.removeElement(user);
                setOptionsVisibility(false);
                refreshLists();
            }
        });
        detailsLabelPanel.add(attractionDetailsLabel);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        buttonPanel.add(updateButton, gridBagConstraints);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        buttonPanel.add(removeButton, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        inputPanel.add(nickLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        inputPanel.add(nickField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        inputPanel.add(firstNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        inputPanel.add(firstNameField, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        inputPanel.add(lastNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        inputPanel.add(lastNameField, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        inputPanel.add(userRoleLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        inputPanel.add(userRoleComboBox, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        inputPanel.add(buttonPanel, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 200);
        this.add(pane, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(inputPanel, gridBagConstraints);
    }

    /**
     * Prepares list of users
     */
    private void prepareList() {
        loadFromDB();
        setOptionsVisibility(false);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        userList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();

                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        User user = (User) userListModel.get(selections[0]);
                        setOptionsValue(user);
                        setOptionsVisibility(true);
                    }
                }
            }
        });
    }

    /**
     * Loads users from database
     */
    private void loadFromDB() {
        userListModel.removeAllElements();
        for (User user : userController.getUsers()) {
            userListModel.addElement(user);
        }
    }

    /**
     * Shows information fields
     * @param visible State of fields
     */
    private void setOptionsVisibility(boolean visible) {
        nickLabel.setVisible(visible);
        firstNameLabel.setVisible(visible);
        lastNameLabel.setVisible(visible);
        userRoleLabel.setVisible(visible);
        nickField.setVisible(visible);
        firstNameField.setVisible(visible);
        lastNameField.setVisible(visible);
        userRoleComboBox.setVisible(visible);
    }

    /**
     * Sets options about specific user
     * @param user Specific user
     */
    private void setOptionsValue(User user) {
        nickField.setText(user.getNick());
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        prepareComboBox(user.getRole());
    }

    /**
     * Prepares ComboBox with user roles
     * @param userRole Role of user
     */
    private void prepareComboBox(UserRoleDictionary userRole) {
        for (int i=0; i<userRoleComboBox.getItemCount(); i++) {
            System.out.println(userRoleComboBox.getItemAt(i));
            System.out.println(userRole);
            if (userRoleComboBox.getItemAt(i).toString() == userRole.toString()) {
                userRoleComboBox.setSelectedIndex(i);
            }
        }
    }

    /**
     * Refreshes lists
     */
    private void refreshLists() {
        userList.revalidate();
        userList.repaint();
    }
}
