package org.example.blackjack.DAO;

import jakarta.persistence.EntityManager;
import org.example.blackjack.model.Deck;
import org.example.blackjack.util.ConnectionManager;

import java.util.List;

public class DeckDaoOrm implements DeckDao{
    @Override
    public List<Deck> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        try{
            return em.createQuery("SELECT d FROM Deck d", Deck.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Deck findById(String id) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            Deck deck = em.find(Deck.class, id);
            return deck;
        } finally {
            em.close();
        }
    }

    @Override
    public void save(Deck deck) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(deck);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Override
    public void update(Deck deck) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(deck);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Override
    public void deleteById(String id) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Deck d = em.find(Deck.class, id);
            if (d != null) {
                em.remove(d);
            }
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
