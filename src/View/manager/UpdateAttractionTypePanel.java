package View.manager;

import Controller.AttractionTypeController;
import Model.AttractionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by blurpek on 15.05.17.
 */
public class UpdateAttractionTypePanel extends JPanel {
    private JComboBox<AttractionType> attractionTypeJComboBox;
    private JTextField nameField;
    private JTextField priceField;
    private AttractionTypeController attractionTypeController;

    public UpdateAttractionTypePanel() {
        attractionTypeController = new AttractionTypeController();
        nameField = new JTextField("new name");
        attractionTypeController = new AttractionTypeController();
        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
        attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        prepareGui();
    }

    private void prepareGui() {
        JButton createButton = new JButton("Update");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndUpdate();
            }
        });

        attractionTypeJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    AttractionType item = (AttractionType) e.getItem();
                    nameField.setText(item.getName());
                }
            }
        });

        this.setLayout(new FlowLayout());
        this.add(attractionTypeJComboBox);
        this.add(nameField);
        this.add(createButton);
        this.setVisible(true);
    }

    private void validateAndUpdate() {
        //TODO: validation
        AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
        if (attractionType == null)
            return;
//        attractionTypeController.updateAttractionType(attractionType.getId(), nameField.getText());
    }
}
