package pl.polkod.restaurator.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pozycja")
public class Pozycja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pozycja_id")
    private int pozycjaId;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "cena")
    private double cena;

    @Column(name = "rodzaj")
    private String rodzaj;

    @Column(name = "waluta")
    private String waluta;

    @Column(name = "opis")
    private String opis;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "skladniki_pozycje",
            joinColumns = @JoinColumn(name = "pozycja_id"),
            inverseJoinColumns = @JoinColumn(name = "skladnik_id"))
    List<Skladnik> skladniki;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "pozycje_zamowienia",
            joinColumns = @JoinColumn(name = "pozycja_id"),
            inverseJoinColumns = @JoinColumn(name = "zamowienie_id"))
    List<Zamowienie> zamowienia;

    public List<Skladnik> getSkladniki() {
        return skladniki;
    }

    public void setSkladniki(List<Skladnik> skladniki) {
        this.skladniki = skladniki;
    }

    public void addSkladnik(Skladnik skladnik) {
        if (skladnik == null) {
            skladniki = new ArrayList<Skladnik>();
        }
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void addZamowienie(Zamowienie zamowienie){
        if(zamowienia==null){
            zamowienia = new ArrayList<>();
        }
        zamowienia.add(zamowienie);
    }

    public int getPozycjaId() {
        return pozycjaId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Pozycja{" +
                "pozycjaId=" + pozycjaId +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", rodzaj='" + rodzaj + '\'' +
                ", waluta='" + waluta + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
