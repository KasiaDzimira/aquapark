package View;

import View.shared.StatisticPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by blurpek on 14.05.17.
 */
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
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel createUserPanel = new JPanel();
        createUserPanel.add(new JLabel("createUserJLabel"));
        cardPanel.setLayout(new CardLayout());
        cardPanel.add(new StatisticPanel(), statisticButton.getText());
        cardPanel.add(createUserPanel, createUserButton.getText());
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
                changeCardTo(createUserButton.getText());
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
//        cl.next(cardPanel);
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
