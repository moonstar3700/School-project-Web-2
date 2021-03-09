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
    String destination = "Overzicht.jsp";

    String titel = request.getParameter("titel");
    String autheur = request.getParameter("autheur");
    String pagina = request.getParameter("pagina");
    String score = request.getParameter("score");

        if (titel.isEmpty() || autheur.isEmpty() || pagina.isEmpty() || score.isEmpty()) {
            destination = "BoekToevoegen.jsp";
        }
        else {
            Boek boek = new Boek(titel, autheur, Integer.parseInt(pagina), Integer.parseInt(score));
            databank.addBoek(boek);
            request.setAttribute("alleboeken", databank.getBoeken());
            destination = "Overzicht.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("alleboeken", databank.getBoeken());
        request.getRequestDispatcher("Overzicht.jsp").forward(request, response);}
}

