package ui.controller;


import domain.DB.BoekenDB;
import domain.model.Boek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            command = "homePage";

            switch (command){
                case "Overzicht":
                    destination = showOverzicht(request, response);
                    break;
                case "BoekToevoegen":
                    destination = "BoekToevoegen.jsp";
                    break;
                case "voegToe":
                    destination = voegToe(request, response);
                    break;
                case "deleteConfirmation":
                    destination = "deleteConfirmation.jsp";
                    break;
                case "delete":
                    destination = delete(request, response);
                    break;
                case "searchPage":
                    destination = zoek(request, response);
                    break;
                case "search":
                    destination = search(request, response);
                    break;
                default:
                    destination = goHome(request);
            }
            request.getRequestDispatcher(destination).forward(request, response);
        }

    private String zoek(HttpServletRequest request, HttpServletResponse response) {

        Cookie c = getCookie(request, "titels");
        if (c != null){
            request.setAttribute("titelCookie", c.getValue());
            return "Zoek.jsp";}
        else return "Zoek.jsp";
    }

    private Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies(); // haalt alle cookies op
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) //
                return cookie;
        }
        return null;
    }


    private String goHome(HttpServletRequest request) {
        String destination;
        request.setAttribute("diksteBoek", databank.diksteBoek());
        request.setAttribute("dunsteBoek", databank.dunsteBoek());
        request.setAttribute("gemiddeldePagina", databank.GemiddeldePagina());
        destination = "index.jsp";
        return destination;
    }

    private String showOverzicht(HttpServletRequest request, HttpServletResponse response) {
        String destination;
        request.setAttribute("alleboeken", databank.getBoeken());

        destination = "Overzicht.jsp";
        return destination;
    }
    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Boek boek = new Boek();
        setTitel(boek, request, errors);
        setAutheur(boek, request, errors);
        setPagina(boek, request, errors);
        setScore(boek, request);

        for(Boek b: databank.getBoeken()){
            if (b.getTitel().equals(boek.getTitel()) && b.getAutheur().equals(boek.getAutheur())){
                errors.add("Dit boek bestaat al in de database");
            }
        }
        if (errors.size() == 0){
            try{
                databank.addBoek(boek);
                return showOverzicht(request, response);
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

        /*String destination;
        String titel = request.getParameter("titel");
        String autheur = request.getParameter("autheur");
        String pagina = request.getParameter("pagina");
        String score = request.getParameter("score");

        if (titel.isEmpty() || autheur.isEmpty() || pagina.isEmpty() || score.isEmpty()) {
            destination = "BoekToevoegen.jsp";
            String error = "Vul alle velden in.";
            request.setAttribute("error", error);
        }
        if (titel.trim().isEmpty()){
            destination = "BoekToevoegen.jsp";
            String errorT = "Titel mag niet leeg zijn.";
            request.setAttribute("error1", errorT);
        }
        if (autheur.trim().isEmpty()){
            destination = "BoekToevoegen.jsp";
            String errorA = "Autheur mag niet leeg zijn.";
            request.setAttribute("error2", errorA);
        }
        if (pagina.isEmpty()){
            destination = "BoekToevoegen.jsp";
            String errorP = "Aantal pagina's mag niet leeg zijn.";
            request.setAttribute("error3", errorP);
        }
        else {
            Boek boek = new Boek(titel, autheur, Integer.parseInt(pagina), Integer.parseInt(score));
            databank.addBoek(boek);
            request.setAttribute("alleboeken", databank.getBoeken());
            destination = "Overzicht.jsp";
        }
        return destination;*/
    }

    private void setTitel(Boek boek, HttpServletRequest request, ArrayList<String> errors){
        String titel = request.getParameter("titel");
        try {
            boek.setTitel(titel);
            //request.setAttribute("titelClass", "has-succes");
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

    private void setScore(Boek boek, HttpServletRequest request){
        int score = Integer.parseInt(request.getParameter("score"));
        boek.setScore(score);
        request.setAttribute("scorepreviuousValue", score);
    }

      private String delete(HttpServletRequest request, HttpServletResponse response){
        String titel = request.getParameter("titel");
        databank.verwijder(titel);
        return showOverzicht(request, response);
    }



    private String search(HttpServletRequest request, HttpServletResponse response){
        String destination;
        String titel = request.getParameter("titel");

        if(titel == null){
            destination = "nietgevonden.jsp";
        }else{
            Boek boeken = databank.vind(titel);
            if(boeken == null){
                destination = "nietgevonden.jsp";
            }else{
                destination = "gevonden.jsp";
                String resultaat1 = "Titel: " + boeken.getTitel();
                String resultaat2 = "Autheur: " + boeken.getAutheur();
                        String resultaat3 = "Aantal pagina's: " + boeken.getPagina();
                        String resultaat4 = "score: " + boeken.getScore();
                        request.setAttribute("resultaat1",resultaat1);
                request.setAttribute("resultaat2",resultaat2);
                request.setAttribute("resultaat3",resultaat3);
                request.setAttribute("resultaat4",resultaat4);
            }
        }
        Cookie cookie = new Cookie("titels", titel); // maakt nieuwe
        response.addCookie(cookie);

        request.getRequestDispatcher(destination);
        return destination;
    }
}

