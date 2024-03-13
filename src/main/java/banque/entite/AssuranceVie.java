package banque.entite;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AssuranceVie extends Compte {
    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false)
    private Double taux;

    // Constructeurs, getters et setters
}
