package pl.polkod.restaurator.PseudoGUI;

import pl.polkod.restaurator.HibernateMaintenance;
import pl.polkod.restaurator.entity.Pozycja;
import pl.polkod.restaurator.entity.Uzytkownik;
import pl.polkod.restaurator.entity.Zamowienie;

import java.util.List;
import java.util.Scanner;

public class Obsluga implements PseudoGUI {

    private static Scanner scanner = new Scanner(System.in);
    private int choice;
    private Uzytkownik activeUser;
    private HibernateMaintenance baza;

    public Obsluga(HibernateMaintenance baza, Uzytkownik user) {
        choice = 0;
        activeUser = user;
        this.baza = baza;
    }

    @Override
    public void wyswietlMenu() {
        while (choice != 4) {
            System.out.println("1. Dodaj zamowienie.");
            System.out.println("2. Modyfikuj zamowienie.");
            System.out.println("3. Wyswietl zamowienie.");
            System.out.println("4. Wyloguj.");
            System.out.print("Wybor: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            wykonajAkcje(choice);
        }
    }

    @Override
    public void wykonajAkcje(int choice) {
        switch (choice) {
            case 1:
                dodajZamowienie();
                break;
            case 2:
                modyfikujZamowienie();
                break;
            case 3:
                wyswietlZamowienia();
                break;
            default:
                System.out.println("Niepoprawna operacja.");
                break;
        }

    }

    private void dodajZamowienie() {
        String nazwa;
        int status;
        System.out.print("Podaj nazwe zamowienia: ");
        nazwa = scanner.nextLine();
        status = baza.dodajZamowienie(activeUser, nazwa);
        switch (status) {
            case 0:
                System.out.println("Dodano zamowienie.");
                break;
            case -1:
                System.out.println("Bledna nazwa.");
                break;
            case 1:
                System.out.println("Istnieje juz aktywne zamowienie o takiej nazwie.");
                break;
        }
    }

    private void wyswietlZamowienia() {
        List<Zamowienie> lista;
        int i, choice = 0;
        System.out.println("Aktywne zamowienia: ");
        lista = baza.pobierzAktywneZamowienia();
        while (choice != -1) {
            i = 1;
            for (Zamowienie x : lista) {
                System.out.println(i + ". " + x.getNazwa());
                i++;
            }
            System.out.print("Wybor [-1 anuluj]: ");
            choice = scanner.nextInt();
            if (choice > lista.size() || choice < 1) {
                continue;
            } else {
                szczegolyZamowienia(lista.get(choice - 1));
            }
        }
    }

    private void szczegolyZamowienia(Zamowienie zam) {
        zam.setPozycje(baza.pobierzPozycjeZZamowienia(zam.getZamowienieId()));
        System.out.println(zam);
    }

    private void modyfikujZamowienie() {
        List<Zamowienie> lista;
        int i, choice = 0;
        int position = 0, order = 0;
        Zamowienie zamowienie;
        System.out.println("Aktywne zamowienia: ");
        while (order != -1) {
            lista = baza.pobierzAktywneZamowienia();
            i = 1;
            for (Zamowienie x : lista) {
                System.out.println(i + ". " + x.getNazwa());
                i++;
            }
            System.out.print("Wybor [-1 anuluj]: ");
            order = scanner.nextInt();
            if (order > lista.size() || order < 1) {
                continue;
            } else {
                zamowienie = lista.get(order - 1);
                szczegolyZamowienia(zamowienie);
                while (choice > 5 || choice < 1) {
                    System.out.println("1. Dodaj pozycje.");
                    System.out.println("2. Usun pozycje. ");
                    System.out.println("3. Zamkniecie rachunku.");
                    System.out.println("4. Anulowanie rachunku.");
                    System.out.println("5. Anuluj");
                    System.out.print("Wybor: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            dodajPozycje(zamowienie);
                            break;
                        case 2:
                            System.out.println("Ktora pozycje?: ");
                            position = scanner.nextInt();
                            scanner.nextLine();
                            baza.usunPozycjeZZamowienia(zamowienie.getZamowienieId(), position - 1);
                            break;
                        case 3:
                            baza.zamkniecieZamowienia(zamowienie.getZamowienieId());
                            break;
                        case 4:
                            baza.anulowanieZamowienia(zamowienie.getZamowienieId());
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Nieprawidlowa operacja.");
                            break;
                    }
                }
            }
        }
    }

    private void dodajPozycje(Zamowienie zamowienie){
        List<Pozycja> pozycje = baza.pobierzPozycje();
        Pozycja poz;
        int choice = 0, i;
        while(choice!=-1){
            i=1;
            for (Pozycja x: pozycje){
                System.out.println(i + ". " + x.getNazwa());
                i++;
            }
            System.out.println("Wybor [-1 zakoncz]: ");
            choice=scanner.nextInt();
            if(choice<1 || choice>pozycje.size()){
                System.err.println("Bledna pozycja");
            }
            else {
                poz = pozycje.get(choice-1);
                baza.dodajPozycjeDoZamowienia(zamowienie.getZamowienieId(), poz.getPozycjaId());
            }
        }
    }
}
