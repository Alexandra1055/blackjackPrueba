package org.example.blackjack.service;

import org.example.blackjack.model.Deck;

import java.util.List;

public interface DeckService {
    Deck createNewDeck(int count);
    Deck getDeck(String id);
    List<Deck> getAllDecks();
}

