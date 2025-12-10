package org.example.blackjack.DAO;

import jakarta.persistence.EntityManager;
import org.example.blackjack.model.Card;
import org.example.blackjack.util.ConnectionManager;

import java.util.List;

public class CardDaoOrm implements CardDao{
    @Override
    public void save(Card card) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(card);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    @Override
    public List<Card> findByDeckId(String deckId) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Card c WHERE c.deck.id = :deckId", Card.class)
                    .setParameter("deckId", deckId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
