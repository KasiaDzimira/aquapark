package View.manager;

import Controller.AttractionTypeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CreateAttractionTypePanel extends JPanel {
    private AttractionTypeController attractionTypeController;
    private GridBagConstraints gridBagConstraints;

    public CreateAttractionTypePanel() {
        attractionTypeController = new AttractionTypeController();
        attractionTypeController = new AttractionTypeController();
        this.setLayout(new GridBagLayout());
        this.gridBagConstraints = new GridBagConstraints();
        prepareGui();
    }

    private void prepareGui() {
        JPanel detailsLabelPanel = new JPanel(new GridLayout(0, 1));
        JLabel attractionDetailsLabel = new JLabel("Attraction type details:");
        attractionDetailsLabel.setForeground(new Color(235, 127, 0));
        attractionDetailsLabel.setFont(new Font(attractionDetailsLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel inputPanel = new JPanel(new GridLayout(2,2, 200 ,10));
        JLabel nameLabel = new JLabel("Name:");
        JLabel priceLabel = new JLabel("Price($):");
        JSpinner priceSpinner = new JSpinner();
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 0, 20,1);
        priceSpinner.setModel(spinnerNumberModel);
        priceSpinner.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));
        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createLineBorder(new Color(172, 240, 242), 2));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton createButton = new JButton("Create");
        createButton.setBackground(new Color(235, 127, 0));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int price = (int) priceSpinner.getValue();

                createAttractionType(name, price);

                JOptionPane.showMessageDialog(null, "Attraction type has been successfully created!");
                nameField.setText("");
                priceSpinner.setValue(1);
            }
        });

        detailsLabelPanel.setBackground(Color.WHITE);
        detailsLabelPanel.setPreferredSize(new Dimension(800, 80));

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(800, 80));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(400, 80));

        nameField.setPreferredSize(new Dimension(400, 70));
        priceSpinner.setPreferredSize(new Dimension(50, 50));
        createButton.setPreferredSize(new Dimension(250, 50));

        detailsLabelPanel.add(attractionDetailsLabel);
        inputPanel.add(nameLabel);
        inputPanel.add(priceLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceSpinner);

        GridBagConstraints gridBagConstraintsBtn = new GridBagConstraints();
        gridBagConstraintsBtn.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsBtn.gridy = 0;
        buttonPanel.add(createButton, gridBagConstraintsBtn);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridy = 0;
        this.add(detailsLabelPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 80, 0);
        this.add(new JSeparator(), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 80, 0);
        this.add(inputPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(buttonPanel, gridBagConstraints);
        this.setVisible(true);
    }

    private void createAttractionType(String name, int price) {
        attractionTypeController.createAttractionType(name, price);
    }

}
