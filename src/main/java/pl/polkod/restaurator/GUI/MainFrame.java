package pl.polkod.restaurator.GUI;

import pl.polkod.restaurator.GUI.components.LoginScreen;
import pl.polkod.restaurator.GUI.components.OverviewScreen;
import pl.polkod.restaurator.HibernateMaintenance;
import pl.polkod.restaurator.entity.Uzytkownik;
import pl.polkod.restaurator.entity.Zamowienie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private HibernateMaintenance hbMainterance;
    private Uzytkownik currentUser;
    private boolean logged;

    private JPanel panel;

    public MainFrame() {
        hbMainterance = new HibernateMaintenance();
        currentUser = null;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        Image logo = new ImageIcon("img/Logo_BLACK.gif").getImage();
        setIconImage(logo);

        if (!logged) {
            panel = new LoginScreen(this);
            add(panel);
        }
    }

    public void logIn(String password) {
        if (!logged) {
            currentUser = hbMainterance.login(password);
        }
        if (currentUser != null && currentUser.getStatus().equals("active")) {
            panel = new OverviewScreen(this);
            getContentPane().removeAll();
            getContentPane().add(panel);
            logged = true;
            return;
        }
        currentUser = null;
        logged = false;
    }

    public void logOff() {
        currentUser = null;
        logged = false;
        panel = new LoginScreen(this);
        getContentPane().removeAll();
        add(panel);
    }

    public void dodajZamowienie(String nazwaZamowienia) {
        if(currentUser != null && logged) {
            hbMainterance.dodajZamowienie(currentUser, nazwaZamowienia);
        } else {
            JOptionPane.showMessageDialog(null, "Błąd tokenu użytkownika.");
        }
    }

    public ArrayList<Zamowienie> getAktywneZamowienia() {
        return (ArrayList<Zamowienie>) hbMainterance.pobierzAktywneZamowienia();
    }
}
