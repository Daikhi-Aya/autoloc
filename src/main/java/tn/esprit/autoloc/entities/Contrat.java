package tn.esprit.autoloc.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)//pour nest pas cree private pour chaque attrébut
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate dateSignature;
    BigDecimal montantTotal;
    boolean valide;

    // 1 Contrat -> 1 Reservation (cote proprietaire, colonne reservation_id)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    // 1 Contrat -> N Paiement (cote inverse, cascade ALL : suppression du contrat -> suppression des paiements)
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Paiement> paiements = new ArrayList<>();
}