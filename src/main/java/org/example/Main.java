package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.engine.spi.EntityEntryFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();


    }
}