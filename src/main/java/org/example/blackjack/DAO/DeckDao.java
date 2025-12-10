package org.example.blackjack.DAO;

import org.example.blackjack.model.Deck;

import java.util.List;

public interface DeckDao {
    List<Deck> findAll();
    Deck findById(String id);
    void save(Deck deck);
    void update(Deck deck);
    void deleteById(String id);
}
