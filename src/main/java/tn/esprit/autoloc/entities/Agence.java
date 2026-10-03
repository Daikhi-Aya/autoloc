package tn.esprit.autoloc.entities;

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
@FieldDefaults(level = AccessLevel.PRIVATE)//pour nest pas cree private pour chaque attrébut
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String nom;
    String ville;
    String adresse;
    String telephone;

    // 1 Agence -> N Vehicule (cote inverse, pas de colonne en base ici)
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    List<Vehicule> vehicules = new ArrayList<>();

    // 1 Agence -> N Employer (cote inverse)
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    List<Employer> employers = new ArrayList<>();
}