package View;

import View.AdminPanel.CreateUserView;
import View.AdminPanel.StatisticsView;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    final static String TAB_STATISTICS = "Statistics";
    final static String TAB_CREATE_USER = "Create user";
    final static String TAB_EDIT_ROLE = "Edit role";

    //manually created
    private Dimension windowSize;

    public AdminView(String windowTitle) {
        this.windowSize = new Dimension(1100, 900);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.centeringWindow();
    }

    public void runWelcome() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel statisticCard = new JPanel();
        statisticCard.add(new Button("statisticsCardButton"));

        JPanel createUserCard = new JPanel();
        createUserCard.add(new Button("createUserButton"));

        JPanel editRoleCard = new JPanel();
        editRoleCard.add(new Button("editRoleButton"));

        CreateUserView createUserView = new CreateUserView();

        tabbedPane.addTab(TAB_STATISTICS, new StatisticsView());
        tabbedPane.addTab(TAB_CREATE_USER, createUserView.renderView());
        tabbedPane.addTab(TAB_EDIT_ROLE, new JPanel());

        tabbedPane.setTabComponentAt(0, createLabel(TAB_STATISTICS));
        tabbedPane.setTabComponentAt(1, createLabel(TAB_CREATE_USER));
        tabbedPane.setTabComponentAt(2, createLabel(TAB_EDIT_ROLE));
        tabbedPane.setBackground(new Color(172, 240, 242));

        Container pane = this.getContentPane();
        pane.setBackground(Color.WHITE);
        pane.add(tabbedPane, BorderLayout.CENTER);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
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

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(230, 40));

        return label;
    }
}
