package View.AdminPanel;

import Controller.UserController;
import Dictionary.UserRoleDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserView extends JPanel {

    public GridBagConstraints gridBagConstraints;

    public CreateUserView() {
        super();
        setOpaque(true);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.gridBagConstraints = new GridBagConstraints();
    }

    public JPanel renderView() {
        String[] userRoles = {
                UserRoleDictionary.ROLE_USER.toString(),
                UserRoleDictionary.ROLE_MANAGER.toString(),
                UserRoleDictionary.ROLE_ADMIN.toString(),
                UserRoleDictionary.ROLE_EMPLOYEE.toString()
        };
        JComboBox userRoleList = new JComboBox(userRoles);
        userRoleList.setSelectedIndex(0);
        JLabel nickLabel = new JLabel("Nick:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel firstNameLabel = new JLabel("First name:");
        JLabel lastNameLabel = new JLabel("Last name:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel telephoneLabel = new JLabel("Telephone:");
        JLabel userRoleLabel = new JLabel("User role:");
        JTextField nickField = new JTextField();
        nickField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JTextField firstNameField = new JTextField();
        firstNameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JTextField lastNameField = new JTextField();
        lastNameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JTextField telephoneField = new JTextField();
        telephoneField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JButton confirm = new JButton("Confirm");
        confirm.setBackground(new Color(235, 127, 0));
        confirm.setForeground(Color.WHITE);

        confirm.addActionListener(new ActionListener() {
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
                    UserRoleDictionary userRole = userController.prepareUserRole((String)userRoleList.getSelectedItem());
                    userController.createClient(nick, password, firstName, lastName, email, telephone, userRole);

                    JOptionPane.showMessageDialog(null, "User has been successfully created!");
                }
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(300, 30);
        firstNameField.setPreferredSize(fieldSize);
        lastNameField.setPreferredSize(fieldSize);
        emailField.setPreferredSize(fieldSize);
        telephoneField.setPreferredSize(fieldSize);
        nickField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        this.add(nickLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(nickField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.add(passwordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(passwordField, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(firstNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        this.add(firstNameField, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        this.add(lastNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        this.add(lastNameField, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        this.add(emailLabel, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        this.add(emailField, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        this.add(telephoneLabel, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        this.add(telephoneField, gridBagConstraints);
        gridBagConstraints.gridy = 12;
        this.add(userRoleLabel, gridBagConstraints);
        gridBagConstraints.gridy = 13;
        this.add(userRoleList, gridBagConstraints);
        gridBagConstraints.gridy = 14;
        this.add(confirm, gridBagConstraints);

        return this;
    }
}
