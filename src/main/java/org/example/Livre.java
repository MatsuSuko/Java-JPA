package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "LIVRE")
public class Livre {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "TITRE", length = 255)
    private String titre;

    @Column(name = "AUTEUR", length = 50)
    private String auteur;

    public Livre() {}

    public Livre(int id, String titre, String auteur) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public static Livre findById(EntityManager em, int id) {
        return em.find(Livre.class, id);
    }

    public static void insertLivre(EntityManager em, String titre, String auteur) {
        Livre livre = new Livre();
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        em.getTransaction().begin();
        em.persist(livre);
        em.getTransaction().commit();
    }

    public static void updateLivreTitre(EntityManager em, int id, String newTitre) {
        Livre livre = findById(em, id);
        if (livre != null) {
            em.getTransaction().begin();
            livre.setTitre(newTitre);
            em.getTransaction().commit();
        }
    }

    public static Livre findByTitre(EntityManager em, String titre) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class);
        query.setParameter("titre", titre);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public static Livre findByAuteur(EntityManager em, String auteur) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class);
        query.setParameter("auteur", auteur);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public static void deleteLivreById(EntityManager em, int id) {
        Livre livre = findById(em, id);
        if (livre != null) {
            em.getTransaction().begin();
            em.remove(livre);
            em.getTransaction().commit();
        }
    }

    public static void printAllBooks(EntityManager em) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l", Livre.class);
        for (Livre livre : query.getResultList()) {
            System.out.printf("%d: %s by %s%n", livre.getId(), livre.getTitre(), livre.getAuteur());
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
        EntityManager em = emf.createEntityManager();

        // Insert a new book
        insertLivre(em, "New Book", "New Author");

        // Update the book with id 5
        updateLivreTitre(em, 5, "Du plaisir dans la cuisine");

        // Find a book by title
        Livre bookByTitle = findByTitre(em, "Guerre et paix");
        System.out.println("Found book by title: " + bookByTitle.getTitre() + " by " + bookByTitle.getAuteur());

        // Find a book by author
        Livre bookByAuthor = findByAuteur(em, "Jules Verne");
        System.out.println("Found book by author: " + bookByAuthor.getTitre() + " by " + bookByAuthor.getAuteur());

        // Delete a book by id
        deleteLivreById(em, 4);

        // Print all books
        printAllBooks(em);

        em.close();
        emf.close();
    }
}

