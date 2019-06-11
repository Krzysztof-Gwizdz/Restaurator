package pl.polkod.restaurator;

import pl.polkod.restaurator.PseudoGUI.Obsluga;
import pl.polkod.restaurator.PseudoGUI.PseudoGUI;
import pl.polkod.restaurator.entity.Uzytkownik;

import java.util.Scanner;

public class MainBezGUI {
    public static HibernateMaintenance noBaza = new HibernateMaintenance();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String pin;
        PseudoGUI pseudoGUI;
        while (true) {
            System.out.print("Podaj pin [-1 exit]: ");
            pin = scanner.nextLine();
            if(pin.equals("-1")){
                break;
            }
            Uzytkownik user = noBaza.login(pin);
            if (user != null) {
                switch (user.getRola()) {
                    case "obsluga":
                        pseudoGUI = new Obsluga(noBaza, user);
                        pseudoGUI.wyswietlMenu();
                        break;
                    default:
                        System.err.println("Error nieznana rola uzytkownika.");
                        break;
                }
            }
            else {
                System.out.println("Bledny pin.");
            }
        }
    }
}
