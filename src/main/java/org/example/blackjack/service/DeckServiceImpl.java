package org.example.blackjack.service;

import com.google.gson.JsonObject;
import org.example.blackjack.DAO.DeckDao;
import org.example.blackjack.DAO.DeckDaoJdbc;
import org.example.blackjack.model.Deck;
import org.example.blackjack.util.ApiProvider;
import org.example.blackjack.util.DaoFactory;

import java.util.List;

public class DeckServiceImpl implements DeckService{
    private final DeckDao deckDao;

    public DeckServiceImpl(DeckDao deckDao) {
        this.deckDao = deckDao;
    }
    @Override
    public Deck createNewDeck(int count) {
        try {
            String url = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=" + count;
            String jsonText = ApiProvider.requestApi(url);

            JsonObject json = ApiProvider.parseToJson(jsonText);

            String deckId = json.get("deck_id").getAsString();
            int remaining = json.get("remaining").getAsInt();

            Deck deck = new Deck(deckId, remaining);
            deckDao.save(deck);

            return deckDao.findById(deckId);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear nuevo deck", e);
        }
    }

    @Override
    public Deck getDeck(String id) {
        return deckDao.findById(id);
    }

    @Override
    public List<Deck> getAllDecks() {
        return deckDao.findAll();
    }

}
