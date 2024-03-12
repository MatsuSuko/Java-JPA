package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TestBibliotheque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
        EntityManager em = emf.createEntityManager();

        // Requête pour extraire un emprunt et tous ses livres associés
        TypedQuery<Emprunt> query1 = em.createQuery("SELECT e FROM Emprunt e JOIN FETCH e.livres WHERE e.id = :id", Emprunt.class);
        query1.setParameter("id", 1);
        Emprunt emprunt = query1.getSingleResult();
        System.out.println("Emprunt : " + emprunt.getId() + ", Client : " + emprunt.getClient().getNom() + ", Livres : ");
        for (Livre livre : emprunt.getLivres()) {
            System.out.println("- " + livre.getTitre());
        }

        // Requête pour extraire tous les emprunts d'un client donné
        TypedQuery<Emprunt> query2 = em.createQuery("SELECT e FROM Emprunt e WHERE e.client.id = :idClient", Emprunt.class);
        query2.setParameter("idClient", 1);
        List<Emprunt> empruntsClient = query2.getResultList();
        System.out.println("Emprunts du client : " + empruntsClient.get(0).getClient().getNom());
        for (Emprunt e : empruntsClient) {
            System.out.println("- Emprunt : " + e.getId() + ", Date début : " + e.getDateDebut() + ", Date fin : " + e.getDateFin());
        }

        em.close();
        emf.close();
    }
}
