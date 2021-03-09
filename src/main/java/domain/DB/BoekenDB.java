package domain.DB;

import domain.model.Boek;

import java.time.LocalDate;
import java.util.ArrayList;

public class BoekenDB {
    private ArrayList<Boek> boeken = new ArrayList<>();
    public BoekenDB(){

        Boek b1 = new Boek("Witcher", "Andrzej Sapkowski", 286,  9);
        Boek b2 = new Boek("Influence", "Robert B. Caldini", 320, 8);
        Boek b3 = new Boek("Moby Dick", "Herman melville", 378, 8);
        Boek b4 = new Boek("Moby Dick", "Herman melville", 378, 8);


        boeken.add(b1);
        boeken.add(b2);
        boeken.add(b3);
        boeken.add(b4);
    }

    public ArrayList<Boek> getBoeken() {return boeken; }
    public void addBoek(Boek boek){boeken.add(boek);}
}
