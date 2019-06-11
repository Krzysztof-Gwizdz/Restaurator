package pl.polkod.restaurator.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skladnik")
public class Skladnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skladnik_id")
    private int skladnikId;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "cena")
    private double cena;

    @Column(name = "waluta")
    private String waluta;

    @Column(name = "opis")
    private String opis;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "skladniki_pozycje",
            joinColumns = @JoinColumn(name = "skladnik_id"),
            inverseJoinColumns = @JoinColumn(name = "pozycja_id"))
    private List<Pozycja> pozycje;

    public Skladnik() {
    }

    public int getSkladnikId() {
        return skladnikId;
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

    public List<Pozycja> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<Pozycja> pozycje) {
        this.pozycje = pozycje;
    }

    public void addPozycja(Pozycja pozycja) {
        if (pozycje == null) {
            pozycje = new ArrayList<Pozycja>();
        }
        pozycje.add(pozycja);
    }

    @Override
    public String toString() {
        return "Skladnik{" +
                "skladnikId=" + skladnikId +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", waluta='" + waluta + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
