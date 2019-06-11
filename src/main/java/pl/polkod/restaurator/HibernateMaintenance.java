package pl.polkod.restaurator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.polkod.restaurator.entity.Pozycja;
import pl.polkod.restaurator.entity.Skladnik;
import pl.polkod.restaurator.entity.Uzytkownik;
import pl.polkod.restaurator.entity.Zamowienie;

import java.sql.Timestamp;
import java.util.List;

public class HibernateMaintenance {
    private SessionFactory factory;
    private Session session;

    public HibernateMaintenance() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pozycja.class)
                .addAnnotatedClass(Skladnik.class)
                .addAnnotatedClass(Uzytkownik.class)
                .addAnnotatedClass(Zamowienie.class)
                .buildSessionFactory();
    }

    public Uzytkownik login(String pin) {
        if (pin.length() == 4) {
            session = factory.getCurrentSession();

            session.beginTransaction();

            List<Uzytkownik> uzytkownicy = session.createQuery("from Uzytkownik u where u.pin='" + pin + "'").getResultList();
            session.getTransaction().commit();
            if (uzytkownicy.size() > 0) {
                if (uzytkownicy.size() > 1) {
                    return null;
                }
                if (uzytkownicy.get(0).getStatus().equals("active")) {
                    return uzytkownicy.get(0);
                }
                return null;
            }
        }
        return null;
    }

    public int dodajZamowienie(Uzytkownik user, String nazwa) {
        List<Zamowienie> exist;
        Zamowienie temp;
        session = factory.getCurrentSession();
        if (nazwa != null && !nazwa.equals("")) {
            session.beginTransaction();
            exist = session.createQuery("from Zamowienie as z where z.nazwa='" + nazwa + "' and z.status='active'").getResultList();
            session.getTransaction().commit();
            if (exist.size() == 0) {
                session = factory.getCurrentSession();
                session.beginTransaction();
                temp = new Zamowienie();
                temp.setNazwa(nazwa);
                temp.setStatus("active");
                temp.setDataUtworzenia(new Timestamp(System.currentTimeMillis()));
                temp.setUzytkownik(user);
                temp.setDataZakonczenia(null);
                session.save(temp);
                session.getTransaction().commit();
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

    public List<Zamowienie> pobierzAktywneZamowienia() {
        List<Zamowienie> lista;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        lista = session.createQuery("from Zamowienie z where status='active'").getResultList();
        session.getTransaction().commit();
        return lista;
    }

    public int usunPozycjeZZamowienia(int zamowienieID, int pozycja) {
        Zamowienie zamowienie;
        session = factory.getCurrentSession();
        session.beginTransaction();
        zamowienie = session.get(Zamowienie.class, zamowienieID);
        zamowienie.getPozycje().remove(pozycja);
        session.getTransaction().commit();
        return 0;
    }

    public int zamkniecieZamowienia(int zamowienieID) {
        return zamowienieZmienStatus(zamowienieID, "closed");
    }

    public int anulowanieZamowienia(int zamowienieID) {
        return zamowienieZmienStatus(zamowienieID, "canceled");
    }

    private int zamowienieZmienStatus(int zamowienieID, String status) {
        Zamowienie zamowienie;
        session = factory.getCurrentSession();
        session.beginTransaction();
        zamowienie = session.get(Zamowienie.class, zamowienieID);
        zamowienie.setStatus(status);
        zamowienie.setDataZakonczenia(new Timestamp(System.currentTimeMillis()));
        session.getTransaction().commit();
        return 0;
    }

    public List<Pozycja> pobierzPozycje() {
        List<Pozycja> pozycje;
        session = factory.getCurrentSession();
        session.beginTransaction();
        pozycje = session.createQuery("from Pozycja").getResultList();
        session.getTransaction().commit();
        return pozycje;
    }

    public int dodajPozycjeDoZamowienia(int zamowienieID, int pozycjaID) {
        Zamowienie zamowienie;
        Pozycja pozycja;
        session = factory.getCurrentSession();
        session.beginTransaction();
        zamowienie = session.get(Zamowienie.class, zamowienieID);
        pozycja = session.get(Pozycja.class, pozycjaID);
        zamowienie.addPozyzja(pozycja);
        session.getTransaction().commit();
        return 0;
    }

    public int dodajPozycje(String nazwa, double cena, String rodzaj, String waluta, String opis) {
        List<Pozycja> pozycje;
        if (nazwa != null && rodzaj != null && waluta != null) {
            session = factory.getCurrentSession();
            session.beginTransaction();
            pozycje = session.createQuery("from Pozycja p where p.nazwa='" + nazwa + "'").getResultList();
            if (pozycje.size() == 0) {
                Pozycja pozycja = new Pozycja();
                pozycja.setNazwa(nazwa);
                pozycja.setCena(cena);
                pozycja.setRodzaj(rodzaj);
                pozycja.setWaluta(waluta);
                pozycja.setOpis(opis);
                session.save(pozycja);
            } else {
                System.err.println("Istnieje taka pozycja.");
                return 2;
            }
            session.getTransaction().commit();
        } else {
            System.err.println("Za malo parametrow");
            return 1;
        }
        return 0;
    }

    public void zmienCenePozycji(int pozycjaID, double nowaCena) {
        Pozycja pozycja;
        session = factory.getCurrentSession();
        session.beginTransaction();
        pozycja = session.get(Pozycja.class, pozycjaID);
        if (pozycja != null) {
            pozycja.setCena(nowaCena);
        }
        session.getTransaction().commit();
    }

    public List<Pozycja> pobierzPozycjeZZamowienia(int zamowienieID) {
        List<Pozycja> pozycje;
        Zamowienie zamowienie;
        session = factory.getCurrentSession();
        session.beginTransaction();
        zamowienie = session.get(Zamowienie.class, zamowienieID);
        pozycje = zamowienie.getPozycje();
        session.getTransaction().commit();
        return pozycje;
    }

    public void nowyUzytkownik(String imie, String nazwisko, String pin, String rola) {
        Uzytkownik uzytkownik;
        if (imie != null && nazwisko != null && pin != null && rola != null) {
            uzytkownik = new Uzytkownik();
            uzytkownik.setImie(imie);
            uzytkownik.setNazwisko(nazwisko);
            uzytkownik.setStatus("active");
            uzytkownik.setPin(pin);
            uzytkownik.setRola(rola);
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(uzytkownik);
            session.getTransaction().commit();
        }
    }

    public void zmienPin(int uzytkownikID, String pin) {
        Uzytkownik uzytkownik;
        if (pin != null) {
            session = factory.getCurrentSession();
            session.beginTransaction();
            uzytkownik = session.get(Uzytkownik.class, uzytkownikID);
            if(uzytkownik!=null){
                uzytkownik.setPin(pin);
            }
            session.getTransaction().commit();
        }
    }
}