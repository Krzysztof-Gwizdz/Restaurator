package pl.polkod.restaurator.GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OverviewSidePanel extends JPanel {

    private OverviewScreen parent;
    private JLabel czas;
    private JButton dodajZamowienieBtn;
    private JButton logOffButton;
    private static final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);

    OverviewSidePanel(OverviewScreen parent) {
        this.parent = parent;

        setLayout(new GridBagLayout());

        czas = new JLabel();
        czas.setFont(font);
        czas.setVerticalAlignment(SwingConstants.TOP);
        czas.setHorizontalAlignment(SwingConstants.CENTER);
        Timer timer = new Timer(1000, new TimePrinter());
        timer.start();

        GridBagConstraints czasConstraints = new GridBagConstraints();
        czasConstraints.gridwidth = 1;
        czasConstraints.gridheight = 5;
        czasConstraints.gridy = 0;
        czasConstraints.gridx = 0;
        czasConstraints.weightx = 100;
        czasConstraints.weighty = 100;
        czasConstraints.fill = GridBagConstraints.BOTH;

        add(czas, czasConstraints);

        dodajZamowienieBtn = new JButton("Dodaj zamówienie");
        dodajZamowienieBtn.setFont(font);
        dodajZamowienieBtn.addActionListener(e -> {
            String nazwaZamowienia;
            StringChooserDialog dialog = new StringChooserDialog();
            if (dialog.showDialog(null, "Podaj nazwe zamowienia")) {
                nazwaZamowienia = dialog.getInputString();
                parent.dodajZamowienie(nazwaZamowienia);
                parent.odswiezOrdersPanel();
            }
        });

        GridBagConstraints dodZamBtnConstraints = new GridBagConstraints();
        dodZamBtnConstraints.gridwidth = 1;
        dodZamBtnConstraints.gridheight = 1;
        dodZamBtnConstraints.gridx = 0;
        dodZamBtnConstraints.gridy = 5;
        dodZamBtnConstraints.weighty=5;
        dodZamBtnConstraints.weightx=100;
        dodZamBtnConstraints.fill = GridBagConstraints.BOTH;

        add(dodajZamowienieBtn, dodZamBtnConstraints);

        logOffButton = new JButton("Wyloguj");
        logOffButton.setFont(font);
        logOffButton.addActionListener(e -> parent.logOff());

        GridBagConstraints logOffBtnConstraints = new GridBagConstraints();
        logOffBtnConstraints.gridwidth = 1;
        logOffBtnConstraints.gridheight = 1;
        logOffBtnConstraints.gridx = 0;
        logOffBtnConstraints.gridy = 6;
        logOffBtnConstraints.weighty = 5;
        logOffBtnConstraints.weightx = 100;
        logOffBtnConstraints.fill = GridBagConstraints.BOTH;

        add(logOffButton, logOffBtnConstraints);
    }

    private class TimePrinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            czas.setText(now.toString());
        }
    }
}
