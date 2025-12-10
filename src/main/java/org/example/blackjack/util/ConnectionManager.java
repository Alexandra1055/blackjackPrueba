package org.example.blackjack.util;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// para jpa/orm, cambiar examenPU por el nombre que salga en persistence.xml <persistence-unit name=" ">
public class ConnectionManager {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("blackjack");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
