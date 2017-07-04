package View.Cashier;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import Controller.UserController;
import Model.User;

/**
 * Cashier view to create new pass
 */
public class CreateNewPassView extends JPanel {
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * Controller for user
     */
    private UserController userController;
    /**
     * List with users
     */
    private JList<User> userList;
    /**
     * Default list of users
     */
    private DefaultListModel userListModel;
    /**
     * Scroll pane
     */
    private JScrollPane pane = new JScrollPane();
    /**
     * Button for extension
     */
    private JButton extensionBtn;

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public CreateNewPassView() {
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        userController = new UserController();
        userListModel = new DefaultListModel();
        this.userList = new JList<>(userListModel);
        this.pane = new JScrollPane();
        this.userList.setFixedCellHeight(50);
        pane.setViewportView(userList);
        this.setBackground(Color.WHITE);
        this.extensionBtn = new JButton("Pass extension");
        renderWindow();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    public JPanel renderWindow() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel cashierDetailsLabel = new JLabel("List of user with active pass:");
        cashierDetailsLabel.setForeground(new Color(235, 127, 0));
        cashierDetailsLabel.setFont(new Font(cashierDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel createPassLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel createPassDetailsLabel = new JLabel("Create pass for existing client:");
        createPassDetailsLabel.setForeground(new Color(235, 127, 0));
        createPassDetailsLabel.setFont(new Font(createPassDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel createPassForNewLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel createPassForNewDetailsLabel = new JLabel("Create pass for new client:");
        createPassForNewDetailsLabel.setForeground(new Color(235, 127, 0));
        createPassForNewDetailsLabel.setFont(new Font(createPassForNewDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(700, 80));
        detailsLabelPanel.add(cashierDetailsLabel);

        createPassLabelPanel.setBackground(Color.WHITE);
        createPassLabelPanel.setPreferredSize(new Dimension(700, 80));
        createPassLabelPanel.add(createPassDetailsLabel);

        createPassForNewLabelPanel.setBackground(Color.WHITE);
        createPassForNewLabelPanel.setPreferredSize(new Dimension(700, 80));
        createPassForNewLabelPanel.add(createPassForNewDetailsLabel);

        prepareList();
        pane.setPreferredSize(new Dimension(100, 200));

        JButton createPassForExistsBtn = new JButton("Create pass");
        JButton createPassForNewBtn = new JButton("Create pass");
        Dimension buttonSize = new Dimension(180, 50);

        // make buttons flat style
        createPassForExistsBtn.setBackground(new Color(235, 127, 0));
        createPassForExistsBtn.setPreferredSize(new Dimension(100, 50));
        createPassForExistsBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        createPassForExistsBtn.setForeground(Color.white);

        createPassForNewBtn.setBackground(new Color(235, 127, 0));
        createPassForNewBtn.setPreferredSize(new Dimension(100, 50));
        createPassForNewBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        createPassForNewBtn.setForeground(Color.white);

        extensionBtn.setForeground(Color.white);
        extensionBtn.setPreferredSize(buttonSize);
        extensionBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        isEnabledBtn(extensionBtn);

        extensionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExtendPassView(userList.getSelectedValue());
            }
        });

        createPassForExistsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreatePassForExistingClientView();
                loadFromDB();
                refreshLists();
            }
        });

        createPassForNewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreatePassForNewClientView();
                loadFromDB();
                refreshLists();
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(pane, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        this.add(extensionBtn, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        this.add(createPassDetailsLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(createPassForExistsBtn, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(createPassForNewDetailsLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        this.add(createPassForNewBtn, gridBagConstraints);
        this.setBackground(Color.white);
        return this;
    }

    /**
     * Prepares list of users
     */
    private void prepareList() {
        loadFromDB();
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        userList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();

                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        isEnabledBtn(extensionBtn);
                    }
                }
            }
        });
    }

    /**
     * Loads users with passes from database
     */
    private void loadFromDB() {
        userListModel.removeAllElements();
        for (User user : userController.getUsersWithPass()) {
            userListModel.addElement(user);
        }
    }

    /**
     * Tests if there is a selection in userList
     * @param button Button to change
     */
    private void isEnabledBtn(JButton button) {
        if (userList.isSelectionEmpty()) {
            button.setEnabled(false);
            button.setBackground(Color.lightGray);
        } else {
            button.setEnabled(true);
            button.setBackground(new Color(235, 127, 0));
        }
    }

    /**
     * Refreshes lists
     */
    private void refreshLists() {
        userList.revalidate();
        userList.repaint();
    }
}
