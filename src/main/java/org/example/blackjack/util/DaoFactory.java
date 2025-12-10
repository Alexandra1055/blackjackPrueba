package org.example.blackjack.util;

import org.example.blackjack.DAO.DeckDao;
import org.example.blackjack.DAO.DeckDaoJdbc;
import org.example.blackjack.DAO.DeckDaoOrm;

public class DaoFactory {
    private static final String IMPL = System.getProperty("dao.impl", "jdbc");

    private static final DeckDao DECK_DAO =
            "orm".equalsIgnoreCase(IMPL) ? new DeckDaoOrm() : new DeckDaoJdbc();

    public static DeckDao getDeckDao() {
        return DECK_DAO;
    }
}
