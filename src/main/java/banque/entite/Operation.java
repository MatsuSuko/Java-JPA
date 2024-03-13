package banque.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private Double montant;
    private String motif;

    @ManyToOne
    private Compte compte;

    public Operation() {}

    public Operation(LocalDateTime date, Double montant, String motif, Compte compte) {
        this.date = date;
        this.montant = montant;
        this.motif = motif;
        this.compte = compte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
