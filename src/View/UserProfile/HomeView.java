package View.UserProfile;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    public HomeView() {}

    public JPanel renderView() {
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

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

        jPanel.add(imageLabel);
        jPanel.add(headingLabel);
        jPanel.add(contentText);
        //JScrollPane jScrollPane = new JScrollPane(jPanel);
//        this.add(imageLabel);
//        this.add(headingLabel);
//        this.add(contentText);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, (int)(width-30), (int)(height-30));
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension((int)(width - 15), (int)(height - 15)));
        contentPane.add(scrollPane);

        this.add(contentPane);
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
