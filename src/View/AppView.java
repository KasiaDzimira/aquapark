package View;

import Controller.UserController;
import Model.User;
import View.UserProfile.HomeView;
import View.manager.ManagerView;
import View.CashierView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main window with log in options
 */
public class AppView extends JFrame {
    /**
     * Main panel
     */
    private JPanel mainPanel;
    /**
     * Dimensions of the window
     */
    private Dimension windowSize;

    /**
     * Constructor for AppView
     * Adjusts settings for the window
     */
    public AppView(String windowTitle) {
        this.windowSize = new Dimension(800, 800);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.mainPanel = new JPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.centeringWindow();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    public void runWelcome() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        JPanel buttonsPanel = new JPanel();
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        JLabel nickLabel = new JLabel("Nick:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField nickField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton logInBtn = new JButton("Log in");
        JButton createBtn = new JButton("Create account");
        JButton forgotPasswordBtn = new JButton("Forgot password");

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateClient createClient = new CreateClient("Create account");
                createClient.renderForm();
            }
        });

        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController userController = new UserController();
                User user = userController.logInAction(nickField.getText(), passwordField.getText());
                if (user != null) {
                    switch (user.getRole()) {
                        case ROLE_USER:
                            //Delete user and userController ~F
                            UserProfileView userProfileView = new UserProfileView("AQUAPARK", user.getNick(), user, userController);
                            dispose();
                            userProfileView.addComponentToPane();
                            break;
                        case ROLE_ADMIN:
                            AdminView adminView = new AdminView("Admin panel");
                            dispose();
                            adminView.runWelcome();
                            break;
                        case ROLE_MANAGER:
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    ManagerView window = new ManagerView("Manager Panel");
                                    dispose();
                                    window.runWelcome();
                                }
                            });
                            break;
                        case ROLE_EMPLOYEE:
                            CashierView cashierView = new CashierView("Cashier panel");
                            dispose();
                            cashierView.runWelcome();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your nick or password are incorrect! Try again!");
                }
            }
        });

        forgotPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Functionality isn't implemented yet!");
            }
        });

        nickField.setPreferredSize(new Dimension(300, 30));
        passwordField.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridy = 0;
        textFieldPanel.add(nickLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        textFieldPanel.add(nickField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        textFieldPanel.add(passwordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        textFieldPanel.add(passwordField, gridBagConstraints);
        buttonsPanel.add(logInBtn);
        buttonsPanel.add(createBtn);
        buttonsPanel.add(forgotPasswordBtn);

        gridBagConstraints.gridy = 0;
        welcomePanel.add(textFieldPanel, gridBagConstraints);
        gridBagConstraints.insets = new Insets(40, 0, 0, 0);
        gridBagConstraints.gridy = 1;
        welcomePanel.add(buttonsPanel, gridBagConstraints);
        mainPanel.add(welcomePanel);
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Centers the window
     */
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
