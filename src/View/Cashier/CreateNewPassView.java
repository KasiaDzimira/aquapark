package View.Cashier;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.UserController;
import Model.User;

public class CreateNewPassView extends JPanel {


    private JPanel buttonsPanel;
    private UserController userController;
    private User userModel;
    private JPanel dataPanel;
    private JPanel usersPanel;
    private Dimension fieldSize = new Dimension(300, 60);

    private JList<User> userList;
    private DefaultListModel userListModel;
    private JScrollPane pane = new JScrollPane();

    public CreateNewPassView() {
        userController = new UserController();
        userListModel = new DefaultListModel();
        userList = new JList<>(userListModel);
        userList.setFixedCellHeight(50);
        this.setBackground(Color.WHITE);
        renderWindow();
    }

    public JPanel renderWindow() {

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;

        //Data panel
        dataPanel = new JPanel(new GridLayout(6, 1));

        JLabel nameLabel = new JLabel("Name");
        JLabel surnameLabel = new JLabel("Surname");
        JLabel emailLabel = new JLabel("Email");
        JLabel phoneLabel = new JLabel("Phone");


        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField("");

        nameField.setPreferredSize(fieldSize);
        nameField.setFont(new Font("Roboto", Font.PLAIN, 20));
        surnameField.setPreferredSize(fieldSize);
        surnameField.setFont(new Font("Roboto", Font.PLAIN, 20));
        emailField.setPreferredSize(fieldSize);
        emailField.setFont(new Font("Roboto", Font.PLAIN, 20));
        phoneField.setPreferredSize(fieldSize);
        phoneField.setFont(new Font("Roboto", Font.PLAIN, 20));


        nameField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        surnameField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        emailField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        phoneField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        dataPanel.setBackground(Color.white);
        dataPanel.add(nameLabel);
        dataPanel.add(nameField);
        dataPanel.add(surnameLabel);
        dataPanel.add(surnameField);
        dataPanel.add(emailLabel);
        dataPanel.add(emailField);
        dataPanel.add(phoneLabel);
        dataPanel.add(phoneField);

        //Users Panel
//        usersPanel = new JPanel(new GridLayout(0, 1));
//
//        prepareList();
//        refreshLists();
//
//        userList.setSize(new Dimension(300, 600));
//        setOptionsVisibility(false);



        // button panel
        Dimension buttonSize = new Dimension(20, 50);
        buttonsPanel = new JPanel(new GridLayout(0, 1));
        buttonsPanel.setBackground(Color.white);
        JButton createBtn = new JButton("Create");
        JButton extensionBtn = new JButton("Extension");
        createBtn.setPreferredSize(new Dimension(150, 20));


        // make buttons flat style
        createBtn.setBorderPainted(false);
        createBtn.setFocusPainted(false);
        createBtn.setBackground(Color.decode("#EB7F00"));
        createBtn.setPreferredSize(buttonSize);
        createBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        createBtn.setForeground(Color.white);
        extensionBtn.setBorderPainted(false);
        extensionBtn.setFocusPainted(false);
        extensionBtn.setBackground(Color.decode("#EB7F00"));
        extensionBtn.setForeground(Color.white);
        extensionBtn.setPreferredSize(buttonSize);
        extensionBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));

        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        buttonsPanel.add(createBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        buttonsPanel.add(extensionBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));

        createBtn.setPreferredSize(new Dimension(150, 20));
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassDetailsView passDetailsView = new PassDetailsView();
            }
        });


        extensionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Will be implemented tomorrow!");
            }
        });



        Container formPanel = new Container();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));


        formPanel.add(dataPanel);


        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(buttonsPanel);


        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }
}
