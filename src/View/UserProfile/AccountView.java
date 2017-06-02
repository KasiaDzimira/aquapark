package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {

    private JPanel buttonsPanel;
    private JPanel personalPanel;
    private JPanel contactPanel;
    private JPanel accommodationPanel;
    private Dimension fieldSize = new Dimension(300, 60);
    public AccountView() {}

    public JPanel renderView() {
        preparePanels();
        Container formPanel = new Container();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel personalLabel = new JLabel("personal");
        JLabel accommodationLabel = new JLabel("accomodation");
        JLabel contactLabel = new JLabel("contact");

        //trzeba gdzies to wrzucic
        JLabel passIdLabel = new JLabel("Pass ID:");
        JTextField passIdField = new JTextField("32910");
        passIdField.setPreferredSize(fieldSize);
        passIdField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));


        personalLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        personalLabel.setForeground(Color.decode("#ACF0F2"));
        accommodationLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        accommodationLabel.setForeground(Color.decode("#ACF0F2"));
        contactLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        contactLabel.setForeground(Color.decode("#ACF0F2"));


        JSeparator personalSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        personalSeparator.setForeground(Color.decode("#ACF0F2"));
        JSeparator accommodationSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        accommodationSeparator.setForeground(Color.decode("#ACF0F2"));
        JSeparator contactSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        contactSeparator.setForeground(Color.decode("#ACF0F2"));


        formPanel.add(Box.createRigidArea(new Dimension(0,30)));
        formPanel.add(personalLabel);
        formPanel.add(personalSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0,20)));
        formPanel.add(personalPanel);

        formPanel.add(Box.createRigidArea(new Dimension(0,30)));
        formPanel.add(accommodationLabel);
        formPanel.add(accommodationSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0,20)));
        formPanel.add(accommodationPanel);

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
    private void preparePanels(){
        preparePersonalPanel();
        prepareAccommodationPanel();
        prepareContactPanel();
        prepareButtonsPanel();
    }

    private void preparePersonalPanel(){
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
    }

    private void prepareAccommodationPanel(){
        accommodationPanel = new JPanel(new GridLayout(0,4));
        ImageIcon imageCity = createImageIcon("image/cityO.png");
        ImageIcon imagePost = createImageIcon("image/postO.png");
        ImageIcon imageStreet = createImageIcon("image/streetO.png");
        ImageIcon imageHouse = createImageIcon("image/houseO.png");
        JLabel cityLabel = new JLabel("", imageCity, SwingConstants.CENTER);
        JLabel postCodeLabel = new JLabel("", imagePost, SwingConstants.CENTER);
        JLabel streetLabel = new JLabel("", imageStreet, SwingConstants.CENTER);
        JLabel houseLabel = new JLabel("", imageHouse, SwingConstants.CENTER);
        JTextField cityField = new JTextField();
        new GhostText(cityField, "City");
        JTextField postCodeField = new JTextField();
        new GhostText(postCodeField, "Post code");
        JTextField streetField = new JTextField();
        new GhostText(streetField, "Street");
        JTextField houseField = new JTextField();
        new GhostText(houseField, "House number");
        cityField.setPreferredSize(fieldSize);
        cityField.setFont(new Font("Roboto", Font.PLAIN, 20));
        postCodeField.setPreferredSize(fieldSize);
        postCodeField.setFont(new Font("Roboto", Font.PLAIN, 20));
        streetField.setPreferredSize(fieldSize);
        streetField.setFont(new Font("Roboto", Font.PLAIN, 20));
        houseField.setPreferredSize(fieldSize);
        houseField.setFont(new Font("Roboto", Font.PLAIN, 20));
        cityField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        postCodeField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        streetField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        houseField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        accommodationPanel.setBackground(Color.white);
        accommodationPanel.add(cityLabel);
        accommodationPanel.add(cityField);
        accommodationPanel.add(postCodeLabel);
        accommodationPanel.add(postCodeField);
        accommodationPanel.add(streetLabel);
        accommodationPanel.add(streetField);
        accommodationPanel.add(houseLabel);
        accommodationPanel.add(houseField);
    }

    private void prepareContactPanel(){
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
    }

    private void prepareButtonsPanel(){
        Dimension buttonSize = new Dimension(20, 50);
        buttonsPanel = new JPanel(new GridLayout(0,5));
        buttonsPanel.setBackground(Color.white);
        JButton acceptBtn = new JButton("Confirm");
        JButton changePassBtn = new JButton("Change password");
        acceptBtn.setPreferredSize(new Dimension(150, 20));
        changePassBtn.setPreferredSize(new Dimension(150, 20));
        changePassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //changeCardTo("buyCard");
                ChangePasswordPanel userAccountView = new ChangePasswordPanel();

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
