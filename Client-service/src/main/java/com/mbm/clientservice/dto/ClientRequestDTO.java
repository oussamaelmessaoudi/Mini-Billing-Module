package com.mbm.clientservice.dto;
import com.mbm.clientservice.dto.validators.CreateClientValidationGroup;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClientRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100,message="name cannot exceed 100 characteres")
    private String nom;

    @NotBlank(message="email is required")
    @Email(message="email should be valid")
    private String email;

    @NotBlank(message="siret is required")
    @Size(max=14,message = "siret cannot exceed 14 numbers")
    private String siret;

    // By declaring the groups, we have ordered that the dateCreation to be part of CreateClientValidationGroup
    @NotBlank(groups = CreateClientValidationGroup.class, message="Registered date is required")
    private LocalDate dateCreation;

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
}
