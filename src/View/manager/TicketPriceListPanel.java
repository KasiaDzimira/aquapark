package View.manager;

import Controller.AttractionController;
import Controller.AttractionTypeController;
import Model.Attraction;
import Model.AttractionType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by blurpek on 15.05.17.
 */
public class TicketPriceListPanel extends JPanel {
    private Dimension panelSize;
    private JList<Attraction> attractionList;
    private JList<AttractionType> attractionTypeList;
    private DefaultListModel attractionListModel;
    private DefaultListModel attractionTypeListModel;
    private JPanel options;

    private ArrayList<Attraction> allAttraction;
    //controllers
    AttractionTypeController attractionTypeController;
    AttractionController attractionController;

    public TicketPriceListPanel(Dimension dimension) {
        panelSize = dimension;
        this.setSize(this.panelSize);
        attractionTypeController = new AttractionTypeController();
        attractionController = new AttractionController();
        allAttraction = new ArrayList<Attraction>();
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
                        System.out.println(selections[0]);
                        System.out.println(attractionListModel.size());
                        System.out.println(attractionListModel);
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
        refreshLists();
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
        options.add(new JButton("dziendobry"));
        options.add(new JTextField("halo"));
    }

    private void mockUp() {
        attractionTypeListModel.addElement(new AttractionType("pierwszyTyp", new BigDecimal(50)));
        attractionTypeListModel.addElement(new AttractionType("drugiTyp", new BigDecimal(100)));
        attractionTypeListModel.addElement(new AttractionType("trzeciTyp", new BigDecimal(150)));
        allAttraction.add(new Attraction(1, (AttractionType) attractionTypeListModel.get(0)));
        allAttraction.add(new Attraction(0, (AttractionType) attractionTypeListModel.get(1)));
        allAttraction.add(new Attraction(1, (AttractionType) attractionTypeListModel.get(1)));

        attractionListModel.addElement(new Attraction(1, (AttractionType) attractionTypeListModel.get(0)));
        attractionListModel.addElement(new Attraction(0, (AttractionType) attractionTypeListModel.get(1)));
        attractionListModel.addElement(new Attraction(1, (AttractionType) attractionTypeListModel.get(1)));
    }
}
