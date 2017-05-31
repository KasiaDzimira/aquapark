package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangePasswordPanel extends JFrame {

    private Dimension windowSize;

    ChangePasswordPanel(){
        this.windowSize = new Dimension(400, 400);
        this.setSize(this.windowSize);
        renderView();
    }

    void CloseFrame(){
        super.dispose();
    }
    void renderView(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel oldPasswordLabel = new JLabel("Old password:");
        JLabel newPasswordLabel = new JLabel("New password:");
        JLabel repeatPasswordLabel = new JLabel("Repeat password:");
        JButton confirmPasswordBtn = new JButton("Confirm");
        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField repeatPasswordField = new JPasswordField();

        Dimension fieldSize = new Dimension(300, 40);
        oldPasswordField.setPreferredSize(fieldSize);
        oldPasswordLabel.setPreferredSize(fieldSize);
        newPasswordField.setPreferredSize(fieldSize);
        newPasswordLabel.setPreferredSize(fieldSize);
        repeatPasswordField.setPreferredSize(fieldSize);
        repeatPasswordLabel.setPreferredSize(fieldSize);


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
