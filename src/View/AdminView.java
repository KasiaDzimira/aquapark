package View;

import View.AdminPanel.CreateUserView;
import View.shared.StatisticPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    //auto-generated
    private JPanel mainPanel;
    private JButton statisticButton;
    private JButton createUserButton;
    private JButton editRoleButton;
    private JPanel cardPanel;

    //manually created
    private Dimension windowSize;

    public AdminView(String windowTitle) {
        this.windowSize = new Dimension(800, 800);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.centeringWindow();
        cardPanel.setLayout(new CardLayout());
        cardPanel.add(new StatisticPanel(), statisticButton.getText());
//        cardPanel.add(statisticButton.getName(), new StatisticPanel());
//        cardPanel.add(createUserButton.getName(), createUserPanel);
    }

    public void runWelcome() {
        statisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCardTo(statisticButton.getText());
            }
        });

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserView createUserView = new CreateUserView();
                createUserView.setName("createUserView");
                cardPanel.add(createUserView.renderView(cardPanel, statisticButton.getText()), createUserButton.getText());
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, createUserButton.getText());
            }
        });

        editRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCardTo(editRoleButton.getText());
            }
        });

        this.add(mainPanel);
        mainPanel.add(cardPanel);
        this.setVisible(true);
    }

    private void changeCardTo(String name) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, name);
        System.out.println("changeCardTo" + name);
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
