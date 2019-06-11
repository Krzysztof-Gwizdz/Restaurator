package pl.polkod.restaurator.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zamowienie")
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zamowienie_id")
    private int zamowienieId;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "status")
    private String status;

    @Column(name = "data_utworzenia")
    private Timestamp dataUtworzenia;

    @Column(name = "data_zamkniecia")
    private Timestamp dataZakonczenia;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "pozycje_zamowienia",
            joinColumns = @JoinColumn(name = "zamowienie_id"),
            inverseJoinColumns = @JoinColumn(name = "pozycja_id"))
    private List<Pozycja> pozycje;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "pracownik_id")
    private Uzytkownik uzytkownik;

    public Zamowienie() {
    }

    public int getZamowienieId() {
        return zamowienieId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Timestamp dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public Timestamp getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Timestamp dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public List<Pozycja> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<Pozycja> pozycje) {
        this.pozycje = pozycje;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public void addPozyzja(Pozycja pozycja) {
        if (pozycje == null) {
            pozycje = new ArrayList<>();
        }
        pozycje.add(pozycja);
    }

    @Override
    public String toString() {
        int lp=1;
        double cena = 0;
        String text = "Nazwa: " + nazwa + "\n" +
                "Status: " + status + "\n" +
                "Data Utworzenia: " + dataUtworzenia + "\n" +
                "Data Zakonczenia: " + dataZakonczenia + "\n" +
                "Uzytkownik: " + uzytkownik.imieNazwisko() + "\n";
        if(pozycje.size()>0) {
            for (Pozycja x : pozycje) {
                text = text + lp + ". " + x.getNazwa() + " " + x.getCena() + "\n";
                cena+=x.getCena();
                lp++;
            }
        }
        text = text + "Razem: " + cena;
        return text;
    }
}
