package View.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeView extends JPanel {

    BufferedImage image;

    public HomeView() {}

    public JPanel renderView() {
        ImageIcon image = createImageIcon("image/homeImage.jpg");
        JLabel imageLabel = new JLabel("", image, SwingConstants.CENTER);
        JLabel headingLabel = new JLabel("HEADING", SwingConstants.CENTER);
        JTextPane contentText = new JTextPane();
        contentText.setText(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n" +
                "Etiam eget velit iaculis, feugiat neque ut, scelerisque elit. Suspendisse non aliquet metus. \n" +
                "Duis ornare nunc est, vel condimentum ligula tincidunt a. Vivamus vel suscipit lorem, ut volutpat \n" +
                " ante. In venenatis id magna eget euismod. Aliquam ultrices malesuada libero eget ultrices. \n" +
                "Nunc eleifend urna sapien, dapibus pharetra neque scelerisque vitae. Vivamus porttitor, augue ut semper \n" +
                "vehicula, ex augue maximus tortor, at posuere justo nulla nec sem. Cras cursus gravida mattis. Aliquam \n" +
                "tellus quam, porttitor ut tempor eget, venenatis eu nibh.\n" +
                "\n" +
                "Morbi vehicula at massa nec lacinia. Interdum et malesuada fames ac ante ipsum primis in faucibus. \n" +
                "Suspendisse neque dui, feugiat in ultrices et, blandit dictum nibh. Donec volutpat aliquam libero vitae \n" +
                "porta. Donec ultricies placerat lacus, vel pellentesque quam dapibus ac. Mauris vel ex quam. Integer auctor \n" +
                "blandit eros congue accumsan. Phasellus bibendum elementum ullamcorper. Nullam hendrerit, tellus id auctor ornare, \n" +
                "enim purus finibus urna, vitae eleifend augue lectus sit amet augue. Donec sed dolor vitae risus mollis commodo.\n" +
                " Duis aliquam, libero quis varius aliquet, libero ligula mattis nulla, nec condimentum risus tortor in leo. Duis porta \n" +
                "tellus vitae quam molestie, nec consectetur ante ultricies. \n");
        headingLabel.setPreferredSize(new Dimension(1100, 50));
        this.add(imageLabel);
        this.add(headingLabel);
        this.add(contentText);
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
