package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HistoryView extends JPanel{

    public HistoryView() {}

    public JPanel renderView() {
        ImageIcon image = createImageIcon("image/calendarImage.jpg");
        JLabel imageLabel = new JLabel("", image, SwingConstants.CENTER);
        JPanel formPanel = new JPanel(new GridLayout(0,2));
        JTextArea statiticsField = new JTextArea("Statistics:" + "\n" + "Slides: 2h15min");
        formPanel.add(imageLabel);
        formPanel.add(statiticsField);
        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = HomeView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
