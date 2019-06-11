package pl.polkod.restaurator.GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class LoginSidePanel extends JPanel {

    private JLabel czas;
    private JButton exitBtn;
    private static final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);

    public LoginSidePanel() {
        czas = new JLabel();
        exitBtn = new JButton("Wyjście");
        exitBtn.addActionListener(e -> System.exit(0));

        czas.setFont(font);
        czas.setHorizontalAlignment(SwingConstants.CENTER);
        czas.setVerticalAlignment(SwingConstants.TOP);
        exitBtn.setFont(font);

        ActionListener timePrinter = new TimePrinter();
        Timer licznikCzasu = new javax.swing.Timer(1000, timePrinter);
        licznikCzasu.start();
        setLayout(new GridBagLayout());

        GridBagConstraints czasConstraints = new GridBagConstraints();
        czasConstraints.weightx = 100;
        czasConstraints.weighty = 100;
        czasConstraints.gridx = 0;
        czasConstraints.gridy = 0;
        czasConstraints.anchor = GridBagConstraints.PAGE_START;
        czasConstraints.gridheight = 5;
        czasConstraints.gridwidth = 1;
        czasConstraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints exitBtnConstraints = new GridBagConstraints();
        exitBtnConstraints.gridwidth = 1;
        exitBtnConstraints.gridheight = 1;
        exitBtnConstraints.gridy = 5;
        exitBtnConstraints.gridx = 0;
        exitBtnConstraints.weighty = 5;
        exitBtnConstraints.weightx = 100;
        exitBtnConstraints.fill = GridBagConstraints.BOTH;

        add(czas, czasConstraints);
        add(exitBtn, exitBtnConstraints);
    }

    private class TimePrinter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            czas.setText(now.toString());
        }
    }
}
