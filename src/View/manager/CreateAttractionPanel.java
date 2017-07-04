package View.manager;

import Controller.AttractionController;
import Controller.AttractionTypeController;
import Model.AttractionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Manager view to create attraction
 */
public class CreateAttractionPanel extends JPanel {
    /**
     * Controller for attraction type
     */
    private AttractionTypeController attractionTypeController;
    /**
     * Controller for attraction
     */
    private AttractionController attractionController;
    /**
     * Constraints for GridBag layout manager
     */
    private GridBagConstraints gridBagConstraints;

    /**
     * Constructor without parameters
     * Adjusts settings of elements to display
     */
    public CreateAttractionPanel() {
        attractionController = new AttractionController();
        attractionTypeController = new AttractionTypeController();
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        prepareGui();
    }

    /**
     * Creates every element of page and adds it to main panel
     */
    private void prepareGui() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Attraction details:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridLayout(2,2, 200 ,10));
        JLabel nameLabel = new JLabel("Name:");
        JLabel attractionTypeLabel = new JLabel("Attraction type:");
        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
        JComboBox<AttractionType> attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        attractionTypeJComboBox.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242)));

        JPanel isActivePanel = new JPanel(new GridLayout(0, 1));
        JRadioButton isActiveRadio = new JRadioButton();
        isActiveRadio.setBackground(Color.WHITE);
        isActiveRadio.setPreferredSize(new Dimension(150, 80));
        isActiveRadio.setText("Is active:");
        isActiveRadio.setHorizontalTextPosition(JRadioButton.LEFT);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton createButton = new JButton("Create");
        createButton.setBackground(new Color(235, 127, 0));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isActive = isActiveRadio.isSelected();
                AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
                String name = nameField.getText();
                createAttraction(name, isActive, attractionType);

                JOptionPane.showMessageDialog(null, "Attraction has been successfully created!");
                nameField.setText("");
                isActiveRadio.setSelected(false);
                attractionTypeJComboBox.setSelectedIndex(0);
            }
        });

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 80));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(800, 80));

        isActivePanel.setBackground(Color.WHITE);
        isActivePanel.setPreferredSize(new Dimension(800, 80));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(400, 80));

        nameField.setPreferredSize(new Dimension(400, 70));
        attractionTypeJComboBox.setPreferredSize(new Dimension(400, 70));
        createButton.setPreferredSize(new Dimension(250, 50));

        detailsLabelPanel.add(attractionDetailsLabel);
        inputPanel.add(nameLabel);
        inputPanel.add(attractionTypeLabel);
        inputPanel.add(nameField);
        inputPanel.add(attractionTypeJComboBox);
        isActivePanel.add(isActiveRadio);

        GridBagConstraints gridBagConstraintsBtn = new GridBagConstraints();
        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        buttonPanel.add(createButton, gridBagConstraintsBtn);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridy = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 50, 0);
        this.add(new JSeparator(), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 50, 0);
        this.add(isActivePanel, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.add(buttonPanel, gridBagConstraints);
        this.setVisible(true);
    }

    /**
     * Creates new attraction
     * @param name Name of the attraction
     * @param isActive State of the attraction
     * @param attractionType Type of the attraction
     */
    private void createAttraction(String name, boolean isActive, AttractionType attractionType) {
        attractionController.createAttraction(name, isActive, attractionType.getId());
    }
}
