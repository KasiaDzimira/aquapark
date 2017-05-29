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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by blurpek on 15.05.17.
 */
public class UpdateAttractionPanel extends JPanel {

    private Dimension panelSize;
    private JList<Attraction> attractionList;
    private JList<AttractionType> attractionTypeList;
    private DefaultListModel attractionListModel;
    private DefaultListModel attractionTypeListModel;
    private JPanel options;
    private JTextField statusField;
    private JTextField nameField;

    private ArrayList<Attraction> allAttraction;
    //controllers
    AttractionTypeController attractionTypeController;
    AttractionController attractionController;

    public UpdateAttractionPanel(Dimension dimension) {
        panelSize = dimension;
        this.setSize(this.panelSize);
        attractionTypeController = new AttractionTypeController();
        attractionController = new AttractionController();
        allAttraction = new ArrayList<Attraction>();
        statusField = new JTextField("status");
        nameField = new JTextField("name");
        prepareGui();
    }

    private void prepareGui() {
        this.setLayout(new FlowLayout());
        options = new JPanel();
        prepareOptions();
        prepareLists();
        attractionTypeList.setSize(new Dimension(panelSize.width/3, panelSize.height/3));
        attractionList.setSize(new Dimension(panelSize.width/3, panelSize.height/3));
        options.setSize(new Dimension(panelSize.width/3, panelSize.height/3));

        options.setVisible(false);
        this.add(attractionTypeList);
        this.add(attractionList);
        this.add(options);
        this.setVisible(true);
    }

    private void validateAndUpdate() {
        //TODO: validation

        int status = Integer.parseInt(statusField.getText());
        Attraction attraction = attractionList.getSelectedValue();
        if (attraction == null)
            return;
        attractionController.updateAttraction(attraction.getId(),nameField.getText() , status, attraction.getAttractionType().getId());
    }

    private void prepareLists() {
        attractionTypeListModel = new DefaultListModel();
        attractionListModel = new DefaultListModel();

        //load data type
        mockUp();
//        loadFromDB();

        attractionTypeList = new JList<>(attractionTypeListModel);
        attractionList = new JList<>(attractionListModel);
        attractionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //adding listeners
        attractionTypeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    filterAttractions(selections);
                    attractionList.clearSelection();
                    options.setVisible(false);
                }
            }
        });

        attractionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean adjust = e.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) e.getSource();
                    int selections[] = list.getSelectedIndices();
                    if (selections != null && selections.length > 0) {
                        Attraction attraction = (Attraction) attractionListModel.get(selections[0]);
                        setOptions(attraction);
                        options.setVisible(true);
                        options.revalidate();
                        options.repaint();
                    }
                }
            }
        });
    }

    private void setOptions(Attraction attraction) {
        statusField.setText(Integer.toString(attraction.getStatus()));
    }

    private void filterAttractions(int[] selectedAttractionTypes) {
        Set<AttractionType> attractionTypeSet = new HashSet<>();
        for (int i = 0; i < selectedAttractionTypes.length; i++) {
            attractionTypeSet.add((AttractionType) attractionTypeListModel.get(selectedAttractionTypes[i]));
        }
//        attractionListModel = new DefaultListModel();
        attractionList.clearSelection();
        attractionListModel.removeAllElements();

        for (Attraction a : allAttraction) {
            if (attractionTypeSet.contains(a.getAttractionType())) {
                attractionListModel.addElement(a);
            }
        }
    }

    public void loadFromDB() {
        for (AttractionType at : attractionTypeController.getAllAttractionTypes()) {
            attractionTypeListModel.addElement(at);
        }
        for (Attraction a : attractionController.getAllAttractions()) {
            allAttraction.add(a);
            attractionListModel.addElement(a);
        }
    }

    private void refreshLists() {
        attractionList.revalidate();
        attractionList.repaint();
        attractionTypeList.revalidate();
        attractionTypeList.repaint();
    }

    private void prepareOptions() {
        BoxLayout boxLayout = new BoxLayout(options, BoxLayout.Y_AXIS);
        options.setLayout(boxLayout);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndUpdate();
            }
        });
        options.add(new JLabel("Status"));
        options.add(statusField);
        options.add(updateButton);
    }

    private void mockUp() {
        attractionTypeListModel.addElement(new AttractionType("pierwszyTyp"));
        attractionTypeListModel.addElement(new AttractionType("drugiTyp"));
        attractionTypeListModel.addElement(new AttractionType("trzeciTyp"));
        allAttraction.add(new Attraction("pierwszy", 1, (AttractionType) attractionTypeListModel.get(0)));
        allAttraction.add(new Attraction("drugi", 0, (AttractionType) attractionTypeListModel.get(1)));
        allAttraction.add(new Attraction("trzeci", 1, (AttractionType) attractionTypeListModel.get(1)));

        attractionListModel.addElement(new Attraction("pierwszy", 1, (AttractionType) attractionTypeListModel.get(0)));
        attractionListModel.addElement(new Attraction("drugi", 0, (AttractionType) attractionTypeListModel.get(1)));
        attractionListModel.addElement(new Attraction("trzeci", 1, (AttractionType) attractionTypeListModel.get(1)));
    }
}
