package View.manager;

import Controller.AttractionTypeController;
import Model.Attraction;
import Model.AttractionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class UpdateAttractionTypePanel extends JPanel {
    private JComboBox<AttractionType> attractionTypeJComboBox;
    private AttractionTypeController attractionTypeController;
    private GridBagConstraints gridBagConstraints;

    public UpdateAttractionTypePanel() {
        attractionTypeController = new AttractionTypeController();
        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
        attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        prepareGui();
    }

    private void prepareGui() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Update attraction type data:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridLayout(2,2, 200 ,10));
        JLabel nameLabel = new JLabel("Name:");
        JLabel priceLabel = new JLabel("Price($):");
        JLabel attractionTypeLabel = new JLabel("Attraction type:");
        JSpinner priceSpinner = new JSpinner();
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 0, 20,1);
        priceSpinner.setModel(spinnerNumberModel);
        priceSpinner.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        JPanel comboBoxPanel = new JPanel(new GridLayout(2, 1));
        setDefaultComboBoxValue(nameField, priceSpinner);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(new Color(235, 127, 0));
        updateButton.setForeground(Color.WHITE);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
//                int price = (int) priceSpinner.getValue();

                updateAttractionType(name);

                int selected = attractionTypeJComboBox.getSelectedIndex();
                attractionTypeJComboBox.getItemAt(selected).setName(name);
//                attractionTypeJComboBox.getItemAt(selected).setPrice(price);

                JOptionPane.showMessageDialog(null, "Attraction type with name " + name + " has been successfully updated!");
                attractionTypeJComboBox.setSelectedIndex(0);
                setDefaultComboBoxValue(nameField, priceSpinner);
            }
        });

        attractionTypeJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    AttractionType item = (AttractionType) e.getItem();
                    nameField.setText(item.getName());
//                    priceSpinner.setValue(item.getPrice());
                }
            }
        });

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 80));

        comboBoxPanel.setBackground(Color.WHITE);
        comboBoxPanel.setPreferredSize(new Dimension(800, 80));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(800, 80));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(400, 80));

        nameField.setPreferredSize(new Dimension(400, 70));
        priceSpinner.setPreferredSize(new Dimension(50, 70));
        attractionTypeJComboBox.setPreferredSize(new Dimension(400, 70));
        updateButton.setPreferredSize(new Dimension(250, 50));

        detailsLabelPanel.add(attractionDetailsLabel);
        comboBoxPanel.add(attractionTypeLabel);
        comboBoxPanel.add(attractionTypeJComboBox);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceSpinner);

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
        gridBagConstraints.insets = new Insets(0, 0, 50, 0);
        this.add(comboBoxPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 80, 0);
        this.add(buttonPanel, gridBagConstraints);
        this.setVisible(true);
    }

    private void updateAttractionType(String name) {
        AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();

        if (attractionType == null)
            return;

        attractionTypeController.updateAttractionType(attractionType.getId(), name);
    }

    private void setDefaultComboBoxValue(JTextField nameField, JSpinner priceSpinner) {
        AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getItemAt(0);
        nameField.setText(attractionType.getName());
//        priceSpinner.setValue(attractionType.getPrice());
    }
}
