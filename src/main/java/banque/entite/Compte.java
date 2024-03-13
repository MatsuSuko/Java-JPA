package banque.entite;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private Double solde;

    @ManyToOne
    private Banque banque;

    @ManyToMany
    @JoinTable(name = "compte_client",
            joinColumns = @JoinColumn(name = "compte_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> clients = new HashSet<>();

    public Compte() {}

    public Compte(String numero, Double solde) {
        this.numero = numero;
        this.solde = solde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
