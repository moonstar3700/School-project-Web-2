package domain.model;

import java.time.LocalDate;

public class Boek {
    String titel;
    String autheur;
    int pagina, score;
    LocalDate van, tot;

    public Boek(String titel, String autheur, int pagina, LocalDate van, LocalDate tot, int score){
    setTitel(titel);
    setAutheur(autheur);
    setPagina(pagina);
    setVan(van);
    setTot(tot);
    setScore(score);
    }
    public void setTitel(String titel) {
        if (titel == null || titel.trim().isEmpty()){
            throw new IllegalArgumentException("titel mag niet leeg zijn");
        }
        this.titel = titel;
    }

    public void setAutheur(String autheur) {
        if (autheur == null || autheur.trim().isEmpty()){
            throw new IllegalArgumentException("autheur mag niet leeg zijn");
        }
        this.autheur = autheur;
    }

    public void setPagina(int pagina) {
        if (pagina <= 0){
            throw new IllegalArgumentException("aantal pagina's moet boven 0 zijn");
        }
        this.pagina = pagina;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setVan(LocalDate van) {
        if (van == null){
            this.van = LocalDate.now();
        }
        this.van = van;
    }

    public void setTot(LocalDate tot) {
        this.tot = tot;
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

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }


}
