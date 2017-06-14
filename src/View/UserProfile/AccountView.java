package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.UserController;
import Model.User;

public class AccountView extends JPanel {

    private JPanel buttonsPanel;
    private UserController userController;
    private User userModel;
    private JPanel personalPanel;
    private JPanel contactPanel;
    private Dimension fieldSize = new Dimension(300, 60);
    //Delete arguments ~F
    public AccountView(User user, UserController userController) {
        this.userModel = user;
        this.userController = userController;
    }

    public JPanel renderView() {

        //PERSONAL PANEL
        personalPanel = new JPanel(new GridLayout(0,4));
        ImageIcon image = createImageIcon("image/personO.png");
        JLabel nameLabel = new JLabel("", image, SwingConstants.CENTER);
        JLabel surnameLabel = new JLabel("", image, SwingConstants.CENTER);
        JTextField nameField = new JTextField();
        new GhostText(nameField, "Name");
        JTextField surnameField = new JTextField("");
        new GhostText(surnameField, "Surname");
        nameField.setPreferredSize(fieldSize);
        nameField.setFont(new Font("Roboto", Font.PLAIN, 20));
        surnameField.setPreferredSize(fieldSize);
        surnameField.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        surnameField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        personalPanel.setBackground(Color.white);
        personalPanel.add(nameLabel);
        personalPanel.add(nameField);
        personalPanel.add(surnameLabel);
        personalPanel.add(surnameField);

        //CONTACT PANEL
        contactPanel = new JPanel(new GridLayout(0,4));
        ImageIcon imagePhone = createImageIcon("image/phoneO.png");
        ImageIcon imageEmail = createImageIcon("image/emailO.png");
        JLabel emailLabel = new JLabel("", imageEmail, SwingConstants.CENTER);
        JLabel phoneLabel = new JLabel("", imagePhone, SwingConstants.CENTER);
        JTextField emailField = new JTextField();
        new GhostText(emailField, "Email");
        JTextField phoneField = new JTextField();
        new GhostText(phoneField, "Phone");
        emailField.setPreferredSize(fieldSize);
        emailField.setFont(new Font("Roboto", Font.PLAIN, 20));
        phoneField.setPreferredSize(fieldSize);
        phoneField.setFont(new Font("Roboto", Font.PLAIN, 20));
        emailField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        phoneField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        contactPanel.setBackground(Color.white);
        contactPanel.add(phoneLabel);
        contactPanel.add(phoneField);
        contactPanel.add(emailLabel);
        contactPanel.add(emailField);

        //BUTTONS PANEL
        Dimension buttonSize = new Dimension(20, 50);
        buttonsPanel = new JPanel(new GridLayout(0,5));
        buttonsPanel.setBackground(Color.white);
        JButton acceptBtn = new JButton("Confirm");
        JButton changePassBtn = new JButton("Change password");
        acceptBtn.setPreferredSize(new Dimension(150, 20));

        //UPDATING INFO FROM DB
        nameField.setText(userModel.getFirstName());
        nameField.setForeground(Color.black);
        surnameField.setText(userModel.getLastName());
        surnameField.setForeground(Color.black);
        emailField.setText(userModel.getEmail());
        emailField.setForeground(Color.black);
        phoneField.setText(userModel.getTelephone());
        phoneField.setForeground(Color.black);


        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                String userSurname = surnameField.getText();
                String userPhone = phoneField.getText();
                String userEmail = emailField.getText();
                userController.updateUser(8, userModel.getNick(), userName, userSurname, "ROLE_USER");
                JOptionPane.showMessageDialog(null, "User has been successfully updated!");
            }
        });
        changePassBtn.setPreferredSize(new Dimension(150, 20));
        changePassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePasswordPanel userAccountView = new ChangePasswordPanel(userModel);
            }
        });

        //make buttons flat style
        acceptBtn.setBorderPainted(false);
        acceptBtn.setFocusPainted(false);
        acceptBtn.setBackground(Color.decode("#EB7F00"));
        acceptBtn.setPreferredSize(buttonSize);
        acceptBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        acceptBtn.setForeground(Color.white);
        changePassBtn.setBorderPainted(false);
        changePassBtn.setFocusPainted(false);
        changePassBtn.setBackground(Color.gray);
        changePassBtn.setForeground(Color.white);
        changePassBtn.setPreferredSize(buttonSize);
        changePassBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));

        buttonsPanel.add(Box.createRigidArea(new Dimension(1,0)));
        buttonsPanel.add(acceptBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1,0)));
        buttonsPanel.add(changePassBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1,0)));

        Container formPanel = new Container();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel personalLabel = new JLabel("personal");
        JLabel contactLabel = new JLabel("contact");

        JLabel passIdLabel = new JLabel("Pass ID:");
        JTextField passIdField = new JTextField("32910");
        passIdField.setPreferredSize(fieldSize);
        passIdField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));

        personalLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        personalLabel.setForeground(Color.decode("#ACF0F2"));
        contactLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        contactLabel.setForeground(Color.decode("#ACF0F2"));

        JSeparator personalSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        personalSeparator.setForeground(Color.decode("#ACF0F2"));
        JSeparator contactSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        contactSeparator.setForeground(Color.decode("#ACF0F2"));

        formPanel.add(Box.createRigidArea(new Dimension(0,30)));
        formPanel.add(personalLabel);
        formPanel.add(personalSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0,20)));
        formPanel.add(personalPanel);

        formPanel.add(Box.createRigidArea(new Dimension(0,30)));
        formPanel.add(contactLabel);
        formPanel.add(contactSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0,20)));
        formPanel.add(contactPanel);

        formPanel.add(Box.createRigidArea(new Dimension(0,20)));
        formPanel.add(buttonsPanel);
        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = HomeView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
