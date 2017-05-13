package View;

import Controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClient extends JFrame {
    private Dimension windowSize;

    public CreateClient(String windowTitle) {
        this.windowSize = new Dimension(400, 400);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.centeringWindow();
        this.setVisible(true);
    }

    public void renderForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel firstNameLabel = new JLabel("First name:");
        JLabel lastNameLabel = new JLabel("Last name:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel telephoneLabel = new JLabel("Telephone:");
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telephoneField = new JTextField();
        JButton confirm = new JButton("Confirm");

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientController clientController = new ClientController();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String telephone = telephoneField.getText();
                clientController.createClient(firstName, lastName, email, telephone);
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(300, 30);
        firstNameField.setPreferredSize(fieldSize);
        lastNameField.setPreferredSize(fieldSize);
        emailField.setPreferredSize(fieldSize);
        telephoneField.setPreferredSize(fieldSize);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        formPanel.add(firstNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        formPanel.add(firstNameField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        formPanel.add(lastNameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        formPanel.add(lastNameField, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        formPanel.add(emailLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        formPanel.add(emailField, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        formPanel.add(telephoneLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        formPanel.add(telephoneField, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        formPanel.add(confirm, gridBagConstraints);

        this.add(formPanel);
    }

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
