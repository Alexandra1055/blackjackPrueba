package org.example.blackjack.DAO;

import org.example.blackjack.model.Deck;
import org.example.blackjack.util.ConnectionManager;
import org.example.blackjack.util.JdbcConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeckDaoJdbc implements DeckDao{
    private static final String SELECT_ALL = "SELECT id FROM decks";
    private static final String SELECT_BY_ID = "SELECT id FROM decks WHERE id = ?";
    private static final String INSERT = "INSERT INTO decks (id, remaining) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE decks SET remaining = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM decks WHERE id = ?";


    @Override
    public List<Deck> findAll() {
        List<Deck> decksResult = new ArrayList<>();
        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                decksResult.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en findAll()", e);
        }
        return decksResult;
    }

    @Override
    public Deck findById(String id) {
        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en findById()", e);
        }
        return null; //porque no lo encontraria
    }

    @Override
    public void save(Deck deck) {
        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, deck.getId());
            ps.setInt(2, deck.getRemaining());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en insert()", e);
        }
    }

    @Override
    public void update(Deck deck) {
        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setInt(1, deck.getRemaining());
            ps.setString(2, deck.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en update()", e);
        }
    }

    @Override
    public void deleteById(String id) {

        try (Connection con = JdbcConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_BY_ID)) {

            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en delete()", e);
        }
    }

    private Deck mapRow(ResultSet rs) throws SQLException {
        Deck deck = new Deck();
        deck.setId(rs.getString("id"));
        deck.setRemaining(rs.getInt("remaining"));
        return deck;
    }
    /*este metodo seria igual que hacer en findById y findAll
    while (rs.next()) {
    Deck deck = new Deck();
    deck.setId(rs.getString("id"));
    deck.setRemaining(rs.getInt("remaining"));
    decksResult.add(deck);
}*/
}
