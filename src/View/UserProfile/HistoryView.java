package View.UserProfile;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HistoryView extends JPanel{

    public HistoryView() {}

    public JPanel renderView() {

        //BUTTONS PANEL
        Dimension buttonSize = new Dimension(20, 50);
        JButton showStatisticsBtn = new JButton("Show");
        showStatisticsBtn.setPreferredSize(new Dimension(150, 20));
        JLabel sinceLabel = new JLabel("Since:");
        JLabel untilLabel = new JLabel("Until:");
        JDateChooser dateChooserFrom = new JDateChooser();
        JDateChooser dateChooserTo = new JDateChooser();
        showStatisticsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Will be implemented tomorrow!");
            }
        });
        showStatisticsBtn.setBorderPainted(false);
        showStatisticsBtn.setFocusPainted(false);
        showStatisticsBtn.setBackground(Color.decode("#EB7F00"));
        showStatisticsBtn.setPreferredSize(buttonSize);
        showStatisticsBtn.setFont(new Font("Lato Heavy", Font.PLAIN, 15));
        showStatisticsBtn.setForeground(Color.white);

        Container formPanel = new Container();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel checkStatisticsLabel = new JLabel("Check your statistics");

        checkStatisticsLabel.setFont(new Font("Lato", Font.PLAIN, 24));
        checkStatisticsLabel.setForeground(Color.decode("#ACF0F2"));

        JSeparator checkStatisticsSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        checkStatisticsSeparator.setForeground(Color.decode("#ACF0F2"));

        JTextArea textArea = new JTextArea(5, 20);
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.add(textArea);
        String statisticsText = "Slides: 2h 10min" + "\n" + "Pools: 5h 14min";
        textArea.setText(statisticsText);
        textArea.setEditable(false);
        statisticsPanel.setBackground(Color.white);
        statisticsPanel.setVisible(false);


        showStatisticsBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                statisticsPanel.setVisible(true);
            }
        });

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(checkStatisticsLabel);
        formPanel.add(checkStatisticsSeparator);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(sinceLabel);
        formPanel.add(dateChooserFrom);
        formPanel.add(untilLabel);
        formPanel.add(dateChooserTo);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(showStatisticsBtn);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(statisticsPanel);
        this.add(formPanel);
        this.setBackground(Color.white);
        return this;
    }
}
