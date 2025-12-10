package org.example.blackjack.DAO;

import org.example.blackjack.model.Card;

import java.util.List;

public interface CardDao {
    void save(Card card);
    List<Card> findByDeckId(String deckId);
}
