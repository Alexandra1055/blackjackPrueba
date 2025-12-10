package org.example.blackjack.service;

import org.example.blackjack.model.Card;

import java.util.List;

public interface CardService {
    void saveCard(Card card);
    List<Card> getCardsByDeck(String deckId);
}
