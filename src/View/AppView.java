package View;

import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppView extends JFrame {
    private JPanel mainPanel;

    private Dimension windowSize;

    public AppView(String windowTitle) {
        this.windowSize = new Dimension(800, 800);
        this.setTitle(windowTitle);
        this.setSize(this.windowSize);
        this.mainPanel = new JPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.centeringWindow();
    }

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
                    HomeView homeView = new HomeView("AQUAPARK");
                    homeView.addComponentToPane();
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
