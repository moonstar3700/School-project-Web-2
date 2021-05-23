package ui.controller;


import domain.DB.BoekenDB;
import domain.model.Boek;
import domain.model.Log;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.util.ArrayList;

@WebServlet("/BoekForm")
public class BoekForm extends HttpServlet {

    private BoekenDB databank = new BoekenDB();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

        private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String destination;
            String command = request.getParameter("command");


            if (command == null)
            command = "";

            switch (command){
                case "Overzicht":
                    destination = showOverzicht(request);
                    break;
                case "BoekToevoegen":
                    destination = "BoekToevoegen.jsp";
                    break;
                case "voegToe":
                    destination = voegToe(request);
                    break;
                case "deleteConfirmation":
                    destination = "deleteConfirmation.jsp";
                    break;
                case "delete":
                    destination = delete(request);
                    break;
                case "searchPage":
                    destination = zoek(request);
                    break;
                case "updatePage":
                    destination = updatePage(request);
                    break;
                case "update":
                    destination = update(request);
                    break;
                case "search":
                    destination = search(request, response);
                    break;
                default:
                    destination = goHome(request);
            }
            request.getRequestDispatcher(destination).forward(request, response);
        }

    /** Implementatie session
     * Bij het opzoeken van een boek dat zich in de database bevindt zal de titel van het boek, samen met het tijdstip waneer
     * het werd opgezocht in het logboek opgeslagen.
     *
     * Zie methode 'search' onderaan voor het oproepen van de 'logboekLijst' methode.
     */

    private void logboekLijst(HttpServletRequest request, String zoek) {
        HttpSession session = request.getSession();
        LocalTime time = LocalTime.now();
        if (session.getAttribute("logboek") == null){
            Log log = new Log(time,zoek);
            ArrayList<Log>logboek = new ArrayList<>();
            logboek.add(log);
            session.setAttribute("logboek", logboek);
        }
        else{
            ArrayList<Log> logboek = (ArrayList<Log>)session.getAttribute("logboek");
            Log log = new Log(time,zoek);
            logboek.add(log);
            session.setAttribute("logboek", logboek);
        }
    }

    private String update(HttpServletRequest request) {
        String titel = request.getParameter("titel");
        if (titel==null || titel.trim().isEmpty()){
            return showOverzicht(request);}
        Boek b = databank.vind(titel);

        ArrayList<String> errors = new ArrayList<>();
        setAutheur(b, request, errors);
        setPagina(b, request, errors);
        setScore(b, request, errors);

        if (errors.size() == 0){
            try{
                return showOverzicht(request);
            }
            catch (IllegalArgumentException exc){
                request.setAttribute("error", exc.getMessage());
                request.setAttribute("titelpreviuousValue", titel);
                return "update.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            request.setAttribute("titelpreviuousValue", titel);
            return "update.jsp";
        }
    }

    private String updatePage(HttpServletRequest request) {
        String titel = request.getParameter("titel");
        String autheur = request.getParameter("autheur");
        String pagina = request.getParameter("pagina");
        String score = request.getParameter("score");

        request.setAttribute("titelpreviuousValue", titel);
        request.setAttribute("autheurpreviuousValue", autheur);
        request.setAttribute("paginapreviuousValue", pagina);
        request.setAttribute("scorepreviousValue", score);

        return "update.jsp";
    }

    private String zoek(HttpServletRequest request) throws UnsupportedEncodingException {

        Cookie c = getCookie(request, "titels");
        if (c != null){
            request.setAttribute("titelCookie", URLDecoder.decode(c.getValue(), "UTF-8"));}

        return "Zoek.jsp";
    }

    /** Implementatie cookie
     *  Het laatst opgezochte zoekterm in de zoek functie van de site wordt in de cookies opgeslagen.
     *  Er wordt geen rekening gehouden of de zoekterm zich in de database bevindt of niet.
     *  Indien men en leeg veld of enkel spaties indient zal niets in de cookies worden opgeslagen.
     *
     *  Voor verdere implementatie cookie zie methode 'search' onderaan.
     */

    private Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies(); // haalt alle cookies op

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key))
                    return cookie;
            }
        }
        return null;
    }

    /**Implementatie berekeningen
     * Indien de index pagina wordt opgevraagd worden de volgende items berekend.
     *      De titel van het boek met het minste aantal pagina's
     *      De titel van het boek met het grootste aantal pagina's
     *      Het gemiddelde aantal pagina's van alle boeken samengenomen
     */

    private String goHome(HttpServletRequest request) {
        String destination;
        if (databank.getSize() != 0) {
            request.setAttribute("diksteBoek", databank.diksteBoek());
            request.setAttribute("dunsteBoek", databank.dunsteBoek());
            request.setAttribute("gemiddeldePagina", databank.GemiddeldePagina());
        }
        destination = "index.jsp";
        return destination;
    }

    private String showOverzicht(HttpServletRequest request) {
        String destination;
        request.setAttribute("alleboeken", databank.getBoeken());

        destination = "Overzicht.jsp";
        return destination;
    }
    private String voegToe(HttpServletRequest request) {
        ArrayList<String> errors = new ArrayList<>();
        Boek boek = new Boek();
        setTitel(boek, request, errors);
        setAutheur(boek, request, errors);
        setPagina(boek, request, errors);
        setScore(boek, request, errors);

        for(Boek b: databank.getBoeken()){
            if (b.getTitel().equals(boek.getTitel())){
                errors.add("Dit boek bestaat al in de database");
            }
        }
        if (errors.size() == 0){
            try{
                databank.addBoek(boek);
                return showOverzicht(request);
            }
            catch (IllegalArgumentException exc){
                request.setAttribute("error", exc.getMessage());
                return "BoekToevoegen.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "BoekToevoegen.jsp";
        }

    }

    private void setTitel(Boek boek, HttpServletRequest request, ArrayList<String> errors){
        String titel = request.getParameter("titel");
        try {
            boek.setTitel(titel);
            request.setAttribute("titelpreviuousValue", titel); // returned de ingevulde waarde bij fouten
        }
        catch (IllegalArgumentException e ){
            errors.add(e.getMessage());
        }
    }

    private void setAutheur(Boek boek, HttpServletRequest request, ArrayList<String> errors){
        String autheur = request.getParameter("autheur");
        try {
            boek.setAutheur(autheur);
            request.setAttribute("autheurpreviuousValue", autheur);
        }
        catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setPagina(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        int pagina;
            try {
                pagina = Integer.parseInt(request.getParameter("pagina"));
                boek.setPagina(pagina);
                request.setAttribute("paginapreviuousValue", pagina);
            }

            catch (NumberFormatException exc){
                errors.add("Voer een getal voor pagina's in");
            }
            catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
    }

    private void setScore(Boek boek, HttpServletRequest request, ArrayList<String> errors){
        int score;
        try {
            score = Integer.parseInt(request.getParameter("score"));
            boek.setScore(score);
            request.setAttribute("scorepreviuousValue", score);
        }
        catch (NumberFormatException exc){
            errors.add("score moet een getal zijn");
        }
        catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

      private String delete(HttpServletRequest request){
        String titel = request.getParameter("titel");
        databank.verwijder(titel);
        return showOverzicht(request);
    }



    private String search(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String destination;
        String titel = request.getParameter("titel");

        if(titel == null || titel.trim().isEmpty()){
            return "nietgevonden.jsp";
        }else{
            Boek boeken = databank.vind(titel);
            if(boeken == null){
                destination = "nietgevonden.jsp";
            }else{

                // Oproepen van 'logboekLijst' methode waneer het gezochte boek zich in de databank bevindt
                logboekLijst(request, boeken.getTitel());
                destination = "gevonden.jsp";
                String resultaat1 = "Titel: " + boeken.getTitel();
                String resultaat2 = "Auteur: " + boeken.getAutheur();
                String resultaat3 = "Aantal pagina's: " + boeken.getPagina();
                String resultaat4 = "score: " + boeken.getScore();
                request.setAttribute("resultaat1",resultaat1);
                request.setAttribute("resultaat2",resultaat2);
                request.setAttribute("resultaat3",resultaat3);
                request.setAttribute("resultaat4",resultaat4);
            }
        }
        Cookie cookie = new Cookie("titels", URLEncoder.encode(titel, "UTF-8")); // maakt nieuwe cookie aan
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return destination;
    }
}

