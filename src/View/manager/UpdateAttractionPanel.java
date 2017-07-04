package View.manager;

import Controller.AttractionController;
import Controller.AttractionTypeController;
import Model.Attraction;
import Model.AttractionType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Manager view for updating attractions
 */
public class UpdateAttractionPanel extends JPanel {
    /**
     * List with attractions
     */
    private JList<Attraction> attractionList;
    /**
     * Default attraction list
     */
    private DefaultListModel attractionListModel;
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;
    /**
     * ArrayList with all attractions
     */
    private ArrayList<Attraction> allAttraction;
    /**
     * Controller for attraction type
     */
    AttractionTypeController attractionTypeController;
    /**
     * Controller for attraction
     */
    AttractionController attractionController;

    /**
     * Constructor without parameters
     * Adjusts settings of elements to display
     */
    public UpdateAttractionPanel() {
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        attractionTypeController = new AttractionTypeController();
        attractionController = new AttractionController();
        allAttraction = new ArrayList<Attraction>();
        attractionListModel = new DefaultListModel();
        attractionList = new JList<>(attractionListModel);
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Update attraction data:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridLayout(4,2, 200 ,10));
        JLabel nameLabel = new JLabel("Name:");
        JLabel attractionTypeLabel = new JLabel("Attraction type:");
        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
        JComboBox<AttractionType> attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        attractionTypeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));

        JRadioButton isActiveRadio = new JRadioButton();
        isActiveRadio.setBackground(Color.WHITE);
        isActiveRadio.setPreferredSize(new Dimension(150, 80));
        isActiveRadio.setText("Is active:");
        isActiveRadio.setHorizontalTextPosition(JRadioButton.LEFT);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(new Color(235, 127, 0));
        updateButton.setForeground(Color.WHITE);

        setOptionsVisibility(nameLabel, nameField, isActiveRadio, updateButton, true);
        AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
        prepareLists(attractionType, nameLabel, nameField, updateButton, isActiveRadio);
        refreshLists();

        JLabel attractionLabel = new JLabel("Select attraction:");

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 80));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(800, 200));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(400, 80));

        nameField.setPreferredSize(new Dimension(400, 70));
        attractionTypeJComboBox.setPreferredSize(new Dimension(400, 70));
        updateButton.setPreferredSize(new Dimension(250, 50));

        attractionTypeJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Dimension panelSize = getSize();
                    attractionList.setSize(new Dimension(panelSize.width/3, panelSize.height/3));
                    AttractionType attractionType = (AttractionType) e.getItem();
                    prepareLists(attractionType, nameLabel, nameField, updateButton, isActiveRadio);
                    refreshLists();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Boolean isActive = isActiveRadio.isSelected();

                updateAttraction(name, isActive);

                JOptionPane.showMessageDialog(null, "Attraction has been successfully updated!");
                setOptionsVisibility(nameLabel, nameField, isActiveRadio, updateButton, false);
                attractionTypeJComboBox.setSelectedIndex(0);
            }
        });

        detailsLabelPanel.add(attractionDetailsLabel);
        inputPanel.add(attractionTypeLabel);
        inputPanel.add(nameLabel);
        inputPanel.add(attractionTypeJComboBox);
        inputPanel.add(nameField);
        inputPanel.add(attractionLabel);
        inputPanel.add(isActiveRadio);
        inputPanel.add(attractionList);

        GridBagConstraints gridBagConstraintsBtn = new GridBagConstraints();
        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        buttonPanel.add(updateButton, gridBagConstraintsBtn);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridy = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 80, 0);
        this.add(new JSeparator(), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(buttonPanel, gridBagConstraints);
        this.setVisible(true);
    }

    /**
     * Updates attraction
     * @param name Name of attraction
     * @param isActive State of attraction
     */
    private void updateAttraction(String name, Boolean isActive) {
        Attraction attraction = attractionList.getSelectedValue();
        if (attraction == null)
            return;

        attractionController.updateAttraction(attraction.getId(), name , isActive, attraction.getAttractionType().getId());
    }

    /**
     * Prepares lists
     * @param attractionType Type of attraction
     * @param nameLabel Name of attraction
     * @param nameField Field with name
     * @param updateButton Button to update
     * @param isActiveRadio Button to choose state of attraction
     */
    private void prepareLists(AttractionType attractionType, JLabel nameLabel, JTextField nameField, JButton updateButton, JRadioButton isActiveRadio) {
        loadFromDB(attractionType);
        setOptionsVisibility(nameLabel, nameField, isActiveRadio, updateButton, false);
        attractionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        attractionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        Attraction attraction = (Attraction) attractionListModel.get(selections[0]);
                        setOptionsValue(nameField, isActiveRadio, attraction);
                        setOptionsVisibility(nameLabel, nameField, isActiveRadio, updateButton, true);
                    }
                }
            }
        });
    }

    /**
     * Loads attractions from database
     * @param attractionType Type of attraction
     */
    private void loadFromDB(AttractionType attractionType) {
        attractionListModel.removeAllElements();
        for (Attraction attraction : attractionController.getAttractionByType(attractionType)) {
            attractionListModel.addElement(attraction);
        }
    }

    /**
     * Makes elements visible
     * @param nameLabel Name of attraction
     * @param nameField Field with name
     * @param isActive State of attraction
     * @param updateButton Button to update
     * @param visible State of elements to show
     */
    private void setOptionsVisibility(JLabel nameLabel, JTextField nameField, JRadioButton isActive, JButton updateButton, boolean visible) {
        nameLabel.setVisible(visible);
        nameField.setVisible(visible);
        isActive.setVisible(visible);
        updateButton.setVisible(visible);
    }

    /**
     * Sets values of options
     * @param nameField Field with name
     * @param isActive State of attraction
     * @param attraction Attraction
     */
    private void setOptionsValue(JTextField nameField, JRadioButton isActive, Attraction attraction) {
        nameField.setText(attraction.getName());
        isActive.setSelected(attraction.getStatus());
    }

    /**
     * Refreshes lists
     */
    private void refreshLists() {
        attractionList.revalidate();
        attractionList.repaint();
    }
}
