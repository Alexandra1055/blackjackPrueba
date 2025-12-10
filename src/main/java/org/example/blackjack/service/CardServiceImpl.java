package org.example.blackjack.service;

import org.example.blackjack.DAO.CardDao;
import org.example.blackjack.DAO.DeckDao;
import org.example.blackjack.model.Card;

import java.util.List;

public class CardServiceImpl implements CardService{
    private final CardDao cardDao;

    public CardServiceImpl(CardDao cardDao) {
        this.cardDao = cardDao;
    }
    @Override
    public void saveCard(Card card) {
        cardDao.save(card);
    }

    @Override
    public List<Card> getCardsByDeck(String deckId) {
        return cardDao.findByDeckId(deckId);
    }
}
