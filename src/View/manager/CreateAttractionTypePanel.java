package View.manager;

import Controller.AttractionTypeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/**
 * Created by blurpek on 15.05.17.
 */
public class CreateAttractionTypePanel extends JPanel {
    private JTextField nameField;
    private JTextField priceField;
    private AttractionTypeController attractionTypeController;

    public CreateAttractionTypePanel() {
        attractionTypeController = new AttractionTypeController();
        nameField = new JTextField("nameField");
        priceField = new JTextField("priceField");
        attractionTypeController = new AttractionTypeController();
        prepareGui();
    }

    private void prepareGui() {
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndCreate();
            }
        });

        this.setLayout(new FlowLayout());
        this.add(nameField);
        this.add(priceField);
        this.add(createButton);
        this.setVisible(true);
    }

    private void validateAndCreate() {
        //TODO: validation

        BigDecimal price = new BigDecimal(Integer.parseInt(priceField.getText()));
        attractionTypeController.createAttractionType(nameField.getText());
    }

}
