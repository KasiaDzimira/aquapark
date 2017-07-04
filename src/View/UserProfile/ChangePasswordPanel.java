package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import Model.User;

/**
 * User view to change password to account
 */
public class ChangePasswordPanel extends JFrame {
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;
    /**
     * Active user
     */
    private User user;

    /**
     * Constructor for ChangePasswordPanel
     * @param user User to change password of
     */
    ChangePasswordPanel(User user){
        this.windowSize = new Dimension(400, 400);
        this.setSize(this.windowSize);
        this.user = user;
        renderView();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    void renderView(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.white);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel oldPasswordLabel = new JLabel("Old password:");
        oldPasswordLabel.setForeground(Color.decode("#225378"));
        JLabel newPasswordLabel = new JLabel("New password:");
        newPasswordLabel.setForeground(Color.decode("#225378"));
        JLabel repeatPasswordLabel = new JLabel("Repeat password:");
        repeatPasswordLabel.setForeground(Color.decode("#225378"));
        JButton confirmPasswordBtn = new JButton("Confirm");
        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField repeatPasswordField = new JPasswordField();

        Dimension fieldSize = new Dimension(300, 40);
        oldPasswordField.setPreferredSize(fieldSize);
        oldPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        oldPasswordLabel.setPreferredSize(fieldSize);
        newPasswordField.setPreferredSize(fieldSize);
        newPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        newPasswordLabel.setPreferredSize(fieldSize);
        repeatPasswordField.setPreferredSize(fieldSize);
        repeatPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#ACF0F2")));
        repeatPasswordLabel.setPreferredSize(fieldSize);

        confirmPasswordBtn.setBorderPainted(false);
        confirmPasswordBtn.setFocusPainted(false);
        confirmPasswordBtn.setBackground(Color.decode("#eb7f00"));
        confirmPasswordBtn.setForeground(Color.white);
        confirmPasswordBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 12));
        confirmPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Arrays.equals(newPasswordField.getPassword(), repeatPasswordField.getPassword())){
                    user.setPassword(repeatPasswordField.getPassword().toString());

                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    //CZY TU TRZEBA COS UPDATEOWAC??
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
                else{
                    JOptionPane.showMessageDialog(null, "New and repeated passwords are not the same!");
                }
            }
        });


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        formPanel.add(oldPasswordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        formPanel.add(oldPasswordField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        formPanel.add(newPasswordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        formPanel.add(newPasswordField, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        formPanel.add(repeatPasswordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        formPanel.add(repeatPasswordField, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        formPanel.add(confirmPasswordBtn, gridBagConstraints);

        this.add(formPanel);
        this.setVisible(true);
    }
}
