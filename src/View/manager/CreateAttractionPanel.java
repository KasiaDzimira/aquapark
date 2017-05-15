package View.manager;

import Controller.AttractionController;
import Controller.AttractionTypeController;
import Model.AttractionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by blurpek on 15.05.17.
 */
public class CreateAttractionPanel extends JPanel {
    private JTextField statusField;
    private JComboBox<AttractionType> attractionTypeJComboBox;
    private AttractionTypeController attractionTypeController;
    private AttractionController attractionController;

    //TODO: uncomment when DB will be fix

    public CreateAttractionPanel() {
        attractionController = new AttractionController();
        statusField = new JTextField("status");
        attractionTypeController = new AttractionTypeController();
//        ArrayList<AttractionType> attractionTypes = (ArrayList<AttractionType>) attractionTypeController.getAllAttractionTypes();
//        AttractionType[] attractionTypes1 = attractionTypes.toArray(new AttractionType[attractionTypes.size()]);
//        attractionTypeJComboBox = new JComboBox<AttractionType>(attractionTypes1);
        prepareGui();
    }

    private void prepareGui() {
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                validateAndCreate();
            }
        });

        this.setLayout(new FlowLayout());
        this.add(statusField);
//        this.add(attractionTypeJComboBox);
        this.add(createButton);
        this.setVisible(true);
    }

    private void validateAndCreate() {
        //TODO: validation

        int status = Integer.parseInt(statusField.getText());
        AttractionType attractionType = (AttractionType) attractionTypeJComboBox.getSelectedItem();
        attractionController.createAttraction(status, attractionType.getId());
    }
}
