package View;

import Controller.UserController;
import Dictionary.UserRoleDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * View for creating new client
 */
public class CreateClient extends JFrame {
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * Informations if client is created
     */
    private boolean isCreated;

    /**
     * Constructor for CreateClient
     * @param windowTitle Title of the window
     */
    public CreateClient(String windowTitle) {
        this.windowSize = new Dimension(400, 500);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.centeringWindow();
        this.setVisible(true);
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    public void renderForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
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
        JButton confirm = new JButton("Confirm");

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
                    userController.createClient(nick, password, firstName, lastName, email, telephone, UserRoleDictionary.ROLE_USER);

                    JOptionPane.showMessageDialog(null, "User has been successfully created! You can to login right now :)");
                    dispose();
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
        gridBagConstraints.gridy = 12;
        formPanel.add(confirm, gridBagConstraints);

        this.add(formPanel);
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
     * Closes window
     */
    private void disposeFrame() {
        this.dispose();
    }
}
