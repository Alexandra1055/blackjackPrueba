package org.example.blackjack.DAO;

import org.example.blackjack.model.Card;
import org.example.blackjack.util.JdbcConnector;

import java.sql.*;
import java.util.List;

public class CardDaoJdbc implements CardDao{
    private static final String INSERT = "INSERT INTO cards (code, image, value, suit,deck_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_DECK_ID = "SELECT id, code, image, value, suit, deck_id FROM cards WHERE deck_id = ?";

    @Override
    public void save(Card card) {
        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, card.getCode());
            ps.setString(2, card.getImage());
            ps.setString(3, card.getValue());
            ps.setString(4, card.getSuit());
            ps.setString(5, card.getDeck().getId());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    card.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en insert()", e);
        }
    }

    @Override
    public List<Card> findByDeckId(String deckId) {
        List<Card> cards = new java.util.ArrayList<>();

        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_DECK_ID)) {

            ps.setString(1, deckId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Card card = new Card();
                    card.setId(rs.getLong("id"));
                    card.setCode(rs.getString("code"));
                    card.setImage(rs.getString("image"));
                    card.setValue(rs.getString("value"));
                    card.setSuit(rs.getString("suit"));

                    var deck = new org.example.blackjack.model.Deck();
                    deck.setId(rs.getString("deck_id"));
                    card.setDeck(deck);

                    cards.add(card);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en findByDeckId()", e);
        }

        return cards;
    }
}
