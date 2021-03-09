package domain.DB;

import domain.model.Boek;

import java.time.LocalDate;
import java.util.ArrayList;

public class BoekenDB {
    private ArrayList<Boek> boeken = new ArrayList<>();
    public BoekenDB(){

        Boek b1 = new Boek("Witcher", "Andrzej Sapkowski", 286, LocalDate.of(2020,1,5),LocalDate.of(2019,8,18), 9);
        Boek b2 = new Boek("Influence", "Robert B. Caldini", 320, LocalDate.of(2019,5,6), LocalDate.of(2019,8,18) ,8);
        Boek b3 = new Boek("Moby Dick", "Herman melville", 378, LocalDate.of(2018,7,19), LocalDate.of(2018, 11, 5), 8);
        Boek b4 = new Boek("Moby Dick", "Herman melville", 378, LocalDate.of(2018,7,19), LocalDate.of(2018, 11, 5), 8);


        boeken.add(b1);
        boeken.add(b2);
        boeken.add(b3);
        boeken.add(b4);
    }

    public ArrayList<Boek> getBoeken() {return boeken; }
    public void addBoek(Boek boek){boeken.add(boek);}
}
