package org.example.blackjack.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.blackjack.model.Deck;
import org.example.blackjack.service.DeckService;
import org.example.blackjack.service.DeckServiceImpl;
import org.example.blackjack.util.DaoFactory;

import java.io.IOException;

@WebServlet("/decks")
public class DeckServlet extends HttpServlet {

    private DeckService deckService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.deckService = new DeckServiceImpl(DaoFactory.getDeckDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // harcodeo el usuario como en gameservlet
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("user", "admin");
        }

        var decks = deckService.getAllDecks();
        req.setAttribute("decks", decks);

        req.getRequestDispatcher("/decks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // nuevo deck a partir del form
        int numberOfDecks = Integer.parseInt(req.getParameter("numberOfDecks"));

        Deck deck = deckService.createNewDeck(numberOfDecks);

        req.setAttribute("decks", deck);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
