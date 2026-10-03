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
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String libelle;

    // cote inverse de la relation N-N (pas de table de jointure ici, deja cote Vehicule)
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    List<Vehicule> vehicules = new ArrayList<>();
}