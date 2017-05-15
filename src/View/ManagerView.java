package View;

import Model.Attraction;
import Model.AttractionType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by blurpek on 15.05.17.
 */
public class ManagerView extends JFrame {
    private Dimension windowSize;
    private JPanel listsPanel;
    private JList<Attraction> attractionList;
    private JList<AttractionType> attractionTypeList;
    private DefaultListModel attractionListModel;
    private DefaultListModel attractionTypeListModel;
    private JPanel options;

    public ManagerView(String title) {
        this.windowSize = new Dimension(800, 800);
        this.setTitle(title);
        this.setSize(this.windowSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        prepareGui();
    }

    private void prepareGui() {
        this.setLayout(new FlowLayout());
        options = new JPanel();
        prepareOptions();
        mockUp();
        centeringWindow();
        attractionTypeList.setSize(new Dimension(windowSize.width/3, windowSize.height/3));
        attractionList.setSize(new Dimension(windowSize.width/3, windowSize.height/3));
        options.setSize(new Dimension(windowSize.width/3, windowSize.height/3));
    }

    private void prepareOptions() {
        options.add(new JButton("dziendobry"));
    }

    private void mockUp() {
        attractionTypeListModel = new DefaultListModel();
        attractionTypeListModel.addElement(new AttractionType("pierwszyTyp", 50));
        attractionTypeListModel.addElement(new AttractionType("drugiTyp", 100));
        attractionTypeListModel.addElement(new AttractionType("trzeciTyp", 150));
        attractionTypeList = new JList<>(attractionTypeListModel);

        attractionListModel = new DefaultListModel();
        attractionListModel.addElement(new Attraction(1, (AttractionType) attractionTypeListModel.get(0)));
        attractionListModel.addElement(new Attraction(0, (AttractionType) attractionTypeListModel.get(1)));
        attractionListModel.addElement(new Attraction(1, (AttractionType) attractionTypeListModel.get(1)));
        attractionList = new JList<>(attractionListModel);
    }

    public void runWelcome() {
        this.add(attractionTypeList);
        this.add(attractionList);
        this.add(options);
        this.setVisible(true);
    }

    private void centeringWindow() {
        // place window in the center
        Dimension center = new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 2);
        this.setLocation(
                (int) (center.getWidth() - (this.windowSize.getWidth() / 2)),
                (int) (center.getHeight() - (this.windowSize.getHeight() / 2)));
    }
}
