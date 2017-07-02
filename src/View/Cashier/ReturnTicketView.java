package View.Cashier;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.*;
import Model.*;


public class ReturnTicketView extends JPanel {

    private JPanel buttonsPanel;
    private TicketController ticketController;
    private Ticket ticketModel;
    private JPanel infoPaymentPanel;
    private JPanel timePanel;
    private Dimension fieldSize = new Dimension(150, 50);

    public ReturnTicketView() {
    }

    public JPanel renderWindow() {
        // Info and payment Panel
        infoPaymentPanel = new JPanel(new GridLayout(0, 4));

        JLabel detailsLabel = new JLabel("Details:", SwingConstants.CENTER);
        JLabel paymentLabel = new JLabel("Additional payment", SwingConstants.CENTER);
        JTextArea detailsArea = new JTextArea();
        JTextField paymentField = new JTextField();
        detailsArea.setEditable(false);
        paymentField.setEditable(false);


        detailsArea.setPreferredSize(fieldSize);
        detailsArea.setFont(new Font("Roboto", Font.PLAIN, 20));
        paymentField.setPreferredSize(fieldSize);
        paymentField.setFont(new Font("Roboto", Font.PLAIN, 20));
        detailsArea.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        paymentField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        infoPaymentPanel.setBackground(Color.white);
        infoPaymentPanel.add(detailsLabel);
        infoPaymentPanel.add(detailsArea);
        infoPaymentPanel.add(paymentLabel);
        infoPaymentPanel.add(paymentField);

        // Time Panel
        timePanel = new JPanel(new GridLayout(0, 4));

        JLabel entryLabel = new JLabel("Entry time", SwingConstants.CENTER);
        JLabel exitLabel = new JLabel("Exit time", SwingConstants.CENTER);
        JTextField entryField = new JTextField();

        JTextField exitField = new JTextField();

        entryField.setPreferredSize(fieldSize);
        entryField.setFont(new Font("Roboto", Font.PLAIN, 20));
        exitField.setPreferredSize(fieldSize);
        exitField.setFont(new Font("Roboto", Font.PLAIN, 20));
        entryField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        exitField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));

        entryField.setEditable(false);
        exitField.setEditable(false);

        timePanel.setBackground(Color.white);
        timePanel.add(entryLabel);
        timePanel.add(entryField);
        timePanel.add(exitLabel);
        timePanel.add(exitField);


        // Buttons Panel
        Dimension buttonSize = new Dimension(20, 50);
        buttonsPanel = new JPanel(new GridLayout(0, 5));
        buttonsPanel.setBackground(Color.white);
        JButton doneButton = new JButton("Done");
        JButton historyButton = new JButton("History");
        doneButton.setPreferredSize(new Dimension(150, 20));


//        paymentField.setText(ticketModel.getLastName());
//        paymentField.setForeground(Color.black);
//        entryField.setText(ticketModel.getEmail());
//        entryField.setForeground(Color.black);
//        exitField.setText(ticketModel.getTelephone());
//        exitField.setForeground(Color.black);


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        historyButton.setPreferredSize(new Dimension(150, 20));
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryView historyView = new HistoryView();
            }
        });

        //make buttons flat style
        doneButton.setBorderPainted(false);
        doneButton.setFocusPainted(false);
        doneButton.setBackground(Color.decode("#EB7F00"));
        doneButton.setPreferredSize(buttonSize);
        doneButton.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        doneButton.setForeground(Color.white);
        historyButton.setBorderPainted(false);
        historyButton.setFocusPainted(false);
        historyButton.setBackground(Color.orange);
        historyButton.setForeground(Color.white);
        historyButton.setPreferredSize(buttonSize);
        historyButton.setFont(new Font("Lato Heavy", Font.PLAIN, 15));

        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        buttonsPanel.add(doneButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        buttonsPanel.add(historyButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));

        Container formPanel = new Container();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel personalLabel = new JLabel("Summary");
        personalLabel.setFont(new Font(personalLabel.getFont().getName(), Font.PLAIN, 30));
        personalLabel.setForeground(new Color(235, 127, 0));

        JTextField passIdField = new JTextField("32910");
        passIdField.setPreferredSize(fieldSize);
        passIdField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));




        JSeparator personalSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        personalSeparator.setForeground(Color.decode("#ACF0F2"));
        JSeparator contactSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        contactSeparator.setForeground(Color.decode("#ACF0F2"));

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(personalLabel);
        formPanel.add(personalSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(infoPaymentPanel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(timePanel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(buttonsPanel);
        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }
}
