package pl.polkod.restaurator;

import pl.polkod.restaurator.entity.Zamowienie;

public class Paragon {

    private Zamowienie zamowienie;

    public Paragon(Zamowienie zamowienie){
        this.zamowienie=zamowienie;
    }

    public String toString(){
        return zamowienie.toString();
    }
}
