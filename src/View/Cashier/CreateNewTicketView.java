package View.Cashier;

import Controller.PassController;
import Controller.TicketController;
import Controller.UserController;
import Controller.WatchController;
import Model.Pass;
import Model.User;
import Model.Watch;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Cashier view to create new ticket
 */
public class CreateNewTicketView extends JPanel {
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
     * Selected user
     */
    private User selectedUser;
    /**
     * Controller for pass
     */
    private PassController passController;
    /**
     * Controller for ticket
     */
    private TicketController ticketController;
    /**
     * Default user list
     */
    private DefaultListModel userListModel;
    /**
     * Controller for watch
     */
    private WatchController watchController;
    /**
     * Scroll pane
     */
    private JScrollPane pane = new JScrollPane();
    /**
     * Button for selling ticket
     */
    JButton createTicketForExistsBtn = new JButton("Sell ticket");

    /**
     * Constructor without parameters
     * Sets elements to display
     */
    public CreateNewTicketView() {
            this.setLayout(new GridBagLayout());
            this.gridBagConstraints = new GridBagConstraints();
            userController = new UserController();
            watchController = new WatchController();
            userListModel = new DefaultListModel();
            passController = new PassController();
            ticketController = new TicketController();
            this.userList = new JList<>(userListModel);
            this.pane = new JScrollPane();
            this.userList.setFixedCellHeight(50);
            pane.setViewportView(userList);
            this.setBackground(Color.WHITE);
            renderWindow();
        }

    /**
     * Creates every element of page and adds it to main panel
     */
    public JPanel renderWindow() {
        JPanel createPassLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel createPassDetailsLabel = new JLabel("Sell ticket for user with active pass:");
        createPassDetailsLabel.setForeground(new Color(235, 127, 0));
        createPassDetailsLabel.setFont(new Font(createPassDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel createPassForNewLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel createPassForNewDetailsLabel = new JLabel("Sell ticket for new client:");
        createPassForNewDetailsLabel.setForeground(new Color(235, 127, 0));
        createPassForNewDetailsLabel.setFont(new Font(createPassForNewDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        createPassLabelPanel.setBackground(Color.WHITE);
        createPassLabelPanel.setPreferredSize(new Dimension(700, 80));
        createPassLabelPanel.add(createPassDetailsLabel);

        createPassForNewLabelPanel.setBackground(Color.WHITE);
        createPassForNewLabelPanel.setPreferredSize(new Dimension(700, 80));
        createPassForNewLabelPanel.add(createPassForNewDetailsLabel);

        prepareList();
        pane.setPreferredSize(new Dimension(100, 200));

        JButton createTicketForNewBtn = new JButton("Sell ticket");

        // make buttons flat style
        createTicketForExistsBtn.setBackground(new Color(235, 127, 0));
        createTicketForExistsBtn.setPreferredSize(new Dimension(100, 50));
        createTicketForExistsBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        createTicketForExistsBtn.setForeground(Color.white);
        isEnabledBtn(createTicketForExistsBtn);

        createTicketForNewBtn.setBackground(new Color(235, 127, 0));
        createTicketForNewBtn.setPreferredSize(new Dimension(100, 50));
        createTicketForNewBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        createTicketForNewBtn.setForeground(Color.white);

        createTicketForExistsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentDate = new Date();
                Watch watch = watchController.getFreeWatch();
                watch.setStatus(2);
                watchController.updateWatch(watch.getId(), 2);
                selectedUser = userList.getSelectedValue();
                ArrayList<Pass> passes = (ArrayList<Pass>) passController.findPassesForUser(selectedUser.getId());
                Pass pass = passes.get(0);

                ticketController.createTicket(currentDate, watch, pass);

                JOptionPane.showMessageDialog(null, "Ticket has been sold!");
            }
        });

        createTicketForNewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellTicketForNewClient sellTicketForNewClient = new SellTicketForNewClient();
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        this.add(createPassLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        this.add(pane, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        this.add(createTicketForExistsBtn, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        this.add(createPassForNewLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(createTicketForNewBtn, gridBagConstraints);
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
                        isEnabledBtn(createTicketForExistsBtn);
                    }
                }
            }
        });
    }

    /**
     * Loads users with tickets from database
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
