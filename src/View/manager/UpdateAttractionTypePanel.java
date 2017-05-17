package View.manager;

import Controller.AttractionTypeController;
import Model.AttractionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

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
        nameField = new JTextField("nameField");
        priceField = new JTextField("priceField");
        attractionTypeController = new AttractionTypeController();
//        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
//        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
//        attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
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

        this.setLayout(new FlowLayout());
//        this.add(attractionTypeJComboBox);
        this.add(nameField);
        this.add(priceField);
        this.add(createButton);
        this.setVisible(true);
    }

    private void validateAndUpdate() {
        //TODO: validation
        AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
        if (attractionType == null)
            return;
        BigDecimal price = new BigDecimal(Integer.parseInt(priceField.getText()));
        attractionTypeController.updateAttractionType(attractionType.getId(), nameField.getText());
    }
}
