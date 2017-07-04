package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User view to buy ticket
 */
public class BuyView extends JPanel{
    /**
     * Buy panel
     */
    private JPanel buyPanel;
    /**
     * Extend pass panel
     */
    private JPanel extendPanel;
    /**
     * Panel for buttons
     */
    private JPanel buttonsPanel;

    /**
     * Constructor without parameters
     */
    public BuyView() {}

    /**
     * Creates every element of page and adds it to main panel
     */
    public JPanel renderView() {

        //BUTTONS PANEL
        Dimension buttonSize = new Dimension(20, 50);
        buttonsPanel = new JPanel(new GridLayout(0, 5));
        buttonsPanel.setBackground(Color.white);
        JButton buyBtn = new JButton("Buy pass");
        JButton extendBtn = new JButton("Extend pass");
        buyBtn.setPreferredSize(new Dimension(150, 20));
        extendBtn.setPreferredSize(new Dimension(150, 20));

        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Will be implemented tomorrow!");
            }
        });
        extendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Will be implemented tomorrow!");
            }
        });
        //make buttons flat style
        buyBtn.setBorderPainted(false);
        buyBtn.setFocusPainted(false);
        buyBtn.setBackground(Color.decode("#EB7F00"));
        buyBtn.setPreferredSize(buttonSize);
        buyBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        buyBtn.setForeground(Color.white);
        extendBtn.setBorderPainted(false);
        extendBtn.setFocusPainted(false);
        extendBtn.setBackground(Color.gray);
        extendBtn.setForeground(Color.white);
        extendBtn.setPreferredSize(buttonSize);
        extendBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));

        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        buttonsPanel.add(buyBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        buttonsPanel.add(extendBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(1, 0)));

        Container formPanel = new Container();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel buyPassLabel = new JLabel("Buy pass");
        JLabel extendPassLabel = new JLabel("Extend your active pass");

        buyPassLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        buyPassLabel.setForeground(Color.decode("#ACF0F2"));
        extendPassLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        extendPassLabel.setForeground(Color.decode("#ACF0F2"));

        JSeparator buyPassSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        buyPassSeparator.setForeground(Color.decode("#ACF0F2"));
        JSeparator extendPassSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        extendPassSeparator.setForeground(Color.decode("#ACF0F2"));

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(buyPassLabel);
        formPanel.add(buyPassSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(buyBtn);

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(extendPassLabel);
        formPanel.add(extendPassSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(extendBtn);

        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }
}
