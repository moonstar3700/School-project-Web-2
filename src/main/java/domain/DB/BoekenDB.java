package domain.DB;

import domain.model.Boek;
import java.util.ArrayList;

public class BoekenDB {
    private ArrayList<Boek> boeken = new ArrayList<>();
    public BoekenDB(){

        Boek b1 = new Boek("Witcher", "Andrzej Sapkowski", 286,  9);
        Boek b2 = new Boek("Influence", "Robert B. Caldini", 320, 8);
        Boek b3 = new Boek("Moby Dick", "Herman melville", 378, 8);

        boeken.add(b1);
        boeken.add(b2);
        boeken.add(b3);
    }

    public ArrayList<Boek> getBoeken() {return boeken; }
    public void addBoek(Boek boek){boeken.add(boek);}

    public Boek diksteBoek(){
        if (boeken.size() == 0)
            return null;
        Boek dikste = boeken.get(0);
        for (Boek boek: boeken){
            if (boek.getPagina() > dikste.getPagina())
                dikste = boek;
        }
        return dikste;
    }

    public Boek dunsteBoek(){
        if (boeken.size() == 0)
            return null;
        Boek dunste = boeken.get(0);
        for (Boek boek: boeken){
            if (boek.getPagina() < dunste.getPagina())
                dunste = boek;
        }
        return dunste;
    }
    
        public void verwijder(String titel){
        boeken.remove(this.vind(titel));
    }


    public Boek vind(String titel) {
        for (Boek boek : boeken) {
            if (boek.heeftTitel(titel)) {
                return boek;
            }
        }
        return null;
    }

}
