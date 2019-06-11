package pl.polkod.restaurator.GUI.components;

import pl.polkod.restaurator.entity.Zamowienie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewOrdersPanel extends JPanel {

    private OverviewScreen parent;
    private ArrayList<Zamowienie> aktywneZamowienia;
    private static final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    public OverviewOrdersPanel(OverviewScreen parent){
        this.parent = parent;
        getAktywneZamowienia();
        for(Zamowienie x: aktywneZamowienia){
            add(new ZamowienieBtn(x));
        }
    }

    private void getAktywneZamowienia(){
        aktywneZamowienia = parent.getAktywneZamowienia();
    }

    private class ZamowienieBtn extends JButton {
        private Zamowienie zamowienie;

        public ZamowienieBtn(Zamowienie zamowienie){
            super();
            this.zamowienie = zamowienie;
            String text = zamowienie.getNazwa() + "\n autor: " + zamowienie.getUzytkownik().getImie()
                    + " " + zamowienie.getUzytkownik().getNazwisko() + "\n utworzono: "
                    + zamowienie.getDataUtworzenia().toLocalDateTime().toString();
            setText(text);
            setFont(font);
        }
    }
}
