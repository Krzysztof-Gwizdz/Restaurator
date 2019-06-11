package pl.polkod.restaurator.GUI.components;

import pl.polkod.restaurator.GUI.MainFrame;
import pl.polkod.restaurator.entity.Zamowienie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewScreen extends JPanel {

    private MainFrame parent;
    private OverviewSidePanel overviewSidePanel;
    private OverviewOrdersPanel overviewOrdersPanel;
    private GridBagConstraints ordersPanelConstraints;

    public OverviewScreen(MainFrame parent){
        this.parent = parent;
        setLayout(new GridBagLayout());

        overviewOrdersPanel = new OverviewOrdersPanel(this);

        ordersPanelConstraints = new GridBagConstraints();
        ordersPanelConstraints.gridwidth = 4;
        ordersPanelConstraints.gridheight = 1;
        ordersPanelConstraints.gridy=0;
        ordersPanelConstraints.gridx=0;
        ordersPanelConstraints.weightx = 300;
        ordersPanelConstraints.weighty = 100;
        ordersPanelConstraints.fill = GridBagConstraints.BOTH;
        ordersPanelConstraints.insets.set(50,50,50,50);

        add(overviewOrdersPanel, ordersPanelConstraints);

        overviewSidePanel = new OverviewSidePanel(this);

        GridBagConstraints sidePanelConstraints = new GridBagConstraints();
        sidePanelConstraints.gridwidth = 1;
        sidePanelConstraints.gridheight = 1;
        sidePanelConstraints.gridx=4;
        sidePanelConstraints.gridy=0;
        sidePanelConstraints.weighty = 100;
        sidePanelConstraints.weightx = 20;
        sidePanelConstraints.fill = GridBagConstraints.BOTH;
        sidePanelConstraints.insets.set(50,50,50,50);

        add (overviewSidePanel, sidePanelConstraints);
    }

    void logOff(){
        parent.logOff();
    }

    public void dodajZamowienie(String nazwaZamowienia) {
        parent.dodajZamowienie(nazwaZamowienia);
    }

    ArrayList<Zamowienie> getAktywneZamowienia(){
        return parent.getAktywneZamowienia();
    }

    void odswiezOrdersPanel(){
        remove(overviewOrdersPanel);
        overviewOrdersPanel = new OverviewOrdersPanel(this);
        add(overviewOrdersPanel, ordersPanelConstraints);
    }
}
