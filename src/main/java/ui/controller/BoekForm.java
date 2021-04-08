package ui.controller;


import domain.DB.BoekenDB;
import domain.model.Boek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                    destination = delete(request, response);
                    break;
                case "searchPage":
                    destination = "Zoek.jsp";
                    break;
                case "search":
                    destination = search(request);
                    break;
                default:
                    destination = goHome(request);
            }
            request.getRequestDispatcher(destination).forward(request, response);
        }


    private String goHome(HttpServletRequest request) {
        String destination;
        request.setAttribute("diksteBoek", databank.diksteBoek());
        request.setAttribute("dunsteBoek", databank.dunsteBoek());
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
        String destination;
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
        return destination;
    }

      private String delete(HttpServletRequest request, HttpServletResponse response){
        String titel = request.getParameter("titel");
        databank.verwijder(titel);
        return showOverzicht(request);
    }



    private String search(HttpServletRequest request){
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
        request.getRequestDispatcher(destination);
        return destination;
    }
}

