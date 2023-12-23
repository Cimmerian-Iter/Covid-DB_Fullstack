package org.polytech.covid.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nom;
    private String prenom;
    private int CenterId;
    private LocalDateTime heure;
    @ManyToOne
    @JoinColumn(name = "center_id")
    private VaccinationCenter center;

    public int getId() {
        return CenterId;
    }

    public void setId(int id) {
        this.CenterId = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getHeure() {
        return heure;
    }

    public void setHeure(LocalDateTime heure) {
        this.heure = heure;
    }

    public VaccinationCenter getCenter() {
        return center;
    }

    public void setCenter(VaccinationCenter center) {
        this.center = center;
    }

}
