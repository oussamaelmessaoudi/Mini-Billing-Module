package com.mbm.clientservice.modele;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Client {
    /*the uuid is going to be the id of  our client and it will be auto generated*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /*Another presistence level that will prevents null names*/
    @NotNull
    private String nom;

    @NotNull
    private String email;

    @NotNull
    @Column(length = 14)
    private String siret;

    @NotNull
    private LocalDate dateCreation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
