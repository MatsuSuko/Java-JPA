package banque.entite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banquePU");
        EntityManager em = emf.createEntityManager();

        // Fermeture de l'EntityManager et de l'EntityManagerFactory
        em.close();
        emf.close();
    }
}
