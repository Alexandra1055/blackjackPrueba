package org.example.blackjack.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.blackjack.DAO.DeckDao;
import org.example.blackjack.model.Deck;
import org.example.blackjack.util.DaoFactory;

import java.io.IOException;

@WebServlet("/")
public class GameServlet extends HttpServlet {
    private DeckDao deckDao;
    @Override
    public void init()throws ServletException {
        super.init();
        deckDao = DaoFactory.getDeckDao();
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //y en doPost igual
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null) {
            final String MOCK_USER = "admin";
            final String MOCK_PASS = "1234"; // solo para documentarlo

            session.setAttribute("user", MOCK_USER);
        }
        var decks = deckDao.findAll();
        String deckId = req.getParameter("id");

        if (deckId != null && !deckId.isBlank()) {
            // 2) Si hay id, cargar el deck desde la BD
            Deck deck = deckDao.findById(deckId);

            // 3) Ponerlo como atributo para la JSP
            req.setAttribute("decks", deck);
        }

        // 4) Forward siempre a index.jsp (ruta relativa al contexto webapp)
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
