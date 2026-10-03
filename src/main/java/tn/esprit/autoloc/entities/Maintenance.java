package tn.esprit.autoloc.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)//pour nest pas cree private pour chaque attrébut
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate dateDebut;
    LocalDate dateFin;
    String description;

    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY )
    @JoinColumn(name = "vehicule_id") //pour redefinir le nom de colone clé etrange nomée vehicule_id(car le column de clé etranger et automatiquement définit)
    Vehicule vehicule;
}