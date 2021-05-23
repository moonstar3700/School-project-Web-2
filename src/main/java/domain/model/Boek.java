package domain.model;

import java.time.LocalDate;

public class Boek {
    private String titel;
    private String autheur;
    private int pagina, score;

    public Boek(String titel, String autheur, int pagina, int score){
    setTitel(titel);
    setAutheur(autheur);
    setPagina(pagina);
    setScore(score);
    }

    public Boek(){

    }

    public void setTitel(String titel) {
        if (titel == null || titel.trim().isEmpty()){
            throw new IllegalArgumentException("Titel mag niet leeg zijn");
        }
        this.titel = titel;
    }

    public void setAutheur(String autheur) {
        if (autheur == null || autheur.trim().isEmpty()){
            throw new IllegalArgumentException("Autheur mag niet leeg zijn");
        }
        this.autheur = autheur;
    }

    public void setPagina(int pagina) {
        if (pagina <= 0){
            throw new IllegalArgumentException("Aantal pagina's moet boven 0 zijn");
        }
        this.pagina = pagina;
    }


    public void setScore(int score) {
        if (score <=0){
            throw new IllegalArgumentException("score mag niet 0 of negatief zijn");
        }
        this.score = score;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutheur() {
        return autheur;
    }

    public int getPagina() {
        return pagina;
    }

    public int getScore() {
        return score;
    }

    public boolean heeftTitel(String titel){
        return titel.equalsIgnoreCase(this.titel);
    }
}
