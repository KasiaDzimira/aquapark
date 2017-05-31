package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {

    public AccountView() {}

    public JPanel renderView() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel nameLabel = new JLabel("Name:");
        JLabel surnameLabel = new JLabel("Surname:");
        JLabel emailLabel = new JLabel("Email address:");
        JLabel cityLabel = new JLabel("City:");
        JLabel postCodeLabel = new JLabel("Post code:");
        JLabel streetLabel = new JLabel("Street:");
        JLabel houseLabel = new JLabel("House number:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel passIdLabel = new JLabel("Pass ID:");

        JTextField nameField = new JTextField("John");
        JTextField surnameField = new JTextField("Kowalski");
        JTextField emailField = new JTextField("JKowalski@gmail.com");
        JTextField cityField = new JTextField("New York");
        JTextField postCodeField = new JTextField("41-200");
        JTextField streetField = new JTextField("Jagiellonska Street");
        JTextField houseField = new JTextField("20");
        JTextField phoneField = new JTextField("500305110");
        JTextField passIdField = new JTextField("32910");
        JButton accept = new JButton("Confirm");
        JButton changePass = new JButton("Change password");

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(300, 20);
        nameField.setPreferredSize(fieldSize);
        surnameField.setPreferredSize(fieldSize);
        emailField.setPreferredSize(fieldSize);
        cityField.setPreferredSize(fieldSize);
        postCodeField.setPreferredSize(fieldSize);
        streetField.setPreferredSize(fieldSize);
        houseField.setPreferredSize(fieldSize);
        phoneField.setPreferredSize(fieldSize);
        passIdField.setPreferredSize(fieldSize);
        accept.setPreferredSize(new Dimension(150, 20));
        changePass.setPreferredSize(new Dimension(150, 20));
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //changeCardTo("buyCard");
                ChangePasswordPanel userAccountView = new ChangePasswordPanel();
            }
        });

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        formPanel.add(nameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        formPanel.add(nameField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        formPanel.add(surnameLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        formPanel.add(surnameField, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        formPanel.add(emailLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        formPanel.add(emailField, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        formPanel.add(cityLabel, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        formPanel.add(cityField, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        formPanel.add(postCodeLabel, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        formPanel.add(postCodeField, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        formPanel.add(streetLabel, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        formPanel.add(streetField, gridBagConstraints);
        gridBagConstraints.gridy = 12;
        formPanel.add(houseLabel, gridBagConstraints);
        gridBagConstraints.gridy = 13;
        formPanel.add(houseField, gridBagConstraints);
        gridBagConstraints.gridy = 14;
        formPanel.add(phoneLabel, gridBagConstraints);
        gridBagConstraints.gridy = 15;
        formPanel.add(phoneField, gridBagConstraints);
        gridBagConstraints.gridy = 16;
        formPanel.add(passIdLabel, gridBagConstraints);
        gridBagConstraints.gridy = 17;
        formPanel.add(passIdField, gridBagConstraints);
        gridBagConstraints.gridy = 18;

        formPanel.add(accept, gridBagConstraints);
        formPanel.add(changePass, gridBagConstraints);
        formPanel.setBackground(Color.white);
        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }
}
