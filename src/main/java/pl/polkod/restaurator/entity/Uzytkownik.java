package pl.polkod.restaurator.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "uzytkownik")
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pracownik_id")
    private int pracownikId;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "status")
    private String status;

    @Column(name = "pin")
    private String pin;

    @Column(name = "rola")
    private String rola;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
                    CascadeType.MERGE, CascadeType.PERSIST},
    mappedBy = "uzytkownik")
    private List<Zamowienie> zamowienia;

    public Uzytkownik() {
    }

    public int getPracownikId() {
        return pracownikId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void addZamowienie(Zamowienie zamowienie){
        if (zamowienia==null){
            zamowienia= new ArrayList<>();
        }
        zamowienia.add(zamowienie);
    }

    public String imieNazwisko(){
        return this.imie + " " + this.nazwisko;
    }
}
