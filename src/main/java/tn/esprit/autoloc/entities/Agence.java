package tn.esprit.autoloc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nom;
    String ville;
    String adresse;
    String telephone;

    // 1 Agence -> N Vehicule
    // On ajoute cascade = CascadeType.ALL pour que les véhicules soient sauvegardés en même temps
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Vehicule> vehicules = new ArrayList<>();

    // 1 Agence -> N Employer
    // On met JsonIgnore ici pour éviter une boucle infinie (Agence -> Employer -> Agence...)
    @JsonIgnore
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    List<Employer> employers = new ArrayList<>();
}