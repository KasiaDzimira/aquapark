package View.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by blurpek on 15.05.17.
 */
public class ManagerView extends JFrame {
    private Dimension windowSize;
    private TicketPriceListPanel ticketPriceListPanel;
    private JPanel buttonsPanel;
    private JPanel cardsPanel;
    private CreateAttractionPanel createAttraction;
    private CreateAttractionTypePanel createAttractionTypePanel;
    private UpdateAttractionTypePanel updateAttractionTypePanel;
    private UpdateAttractionPanel updateAttractionPanel;

    public ManagerView(String title) {
        this.windowSize = new Dimension(800, 800);
        this.setTitle(title);
        this.setSize(this.windowSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ticketPriceListPanel = new TicketPriceListPanel(new Dimension(windowSize.width, windowSize.height));
        buttonsPanel = new JPanel();
        cardsPanel = new JPanel();
        createAttraction = new CreateAttractionPanel();
        createAttractionTypePanel = new CreateAttractionTypePanel();
        updateAttractionTypePanel = new UpdateAttractionTypePanel();
        updateAttractionPanel = new UpdateAttractionPanel(this.windowSize);
        prepareGui();
    }

    private void prepareGui() {
        prepareCards();
        prepareButtons();
    }

    private void prepareCards() {
        cardsPanel.setLayout(new CardLayout());
        cardsPanel.add(createAttraction, "createAttraction");
        cardsPanel.add(ticketPriceListPanel, "ticketPriceList");
        cardsPanel.add(createAttractionTypePanel, "createAttractionType");
        cardsPanel.add(updateAttractionTypePanel, "updateAttractionType");
        cardsPanel.add(updateAttractionPanel, "updateAttraction");
    }

    private void prepareButtons() {
        buttonsPanel.setLayout(new FlowLayout());
        JButton ticketPriceListButton = new JButton("Ticket Price List");
        JButton createButton = new JButton("Create Attraction");
        JButton createAttractionTypeButton = new JButton("Create Attraction Type");
        JButton updateAttractionTypeButton = new JButton("Update AttractionType");
        JButton updateAttractionButton = new JButton("Update Attraction");
        ticketPriceListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ticketPriceListPanel.loadFromDB();
                changeCardTo("ticketPriceList");
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCardTo("createAttraction");
            }
        });
        createAttractionTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCardTo("createAttractionType");
            }
        });
        updateAttractionTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCardTo("updateAttractionType");
            }
        });
        updateAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCardTo("updateAttraction");
            }
        });
        buttonsPanel.add(createButton);
        buttonsPanel.add(ticketPriceListButton);
        buttonsPanel.add(createAttractionTypeButton);
        buttonsPanel.add(updateAttractionTypeButton);
        buttonsPanel.add(updateAttractionButton);
    }

    private void changeCardTo(String name) {
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, name);
    }

    public void runWelcome() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(buttonsPanel);
        this.add(cardsPanel);
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
