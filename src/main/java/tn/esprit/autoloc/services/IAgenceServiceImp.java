package tn.esprit.autoloc.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class IAgenceServiceImp implements IAgenceService {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> consulterAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence consulterAgenceById(Long idAgence) {
        return agenceRepository.findById(idAgence).orElse(null);
    }

    @Override
    public void supprimerAgenceById(Long idAgence) {
        agenceRepository.deleteById(idAgence);
    }

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        List<Vehicule> vehicules = agence.getVehicules() != null
                ? agence.getVehicules()
                : new ArrayList<>();

        // 1. enregistrer l'agence (elle obtient son id)
        Agence agenceEnregistree = agenceRepository.save(agence);

        // 2. enregistrer chaque véhicule en l'affectant à cette agence
        for (Vehicule vehicule : vehicules) {
            vehicule.setAgence(agenceEnregistree);
            vehiculeRepository.save(vehicule);
        }
        return agenceEnregistree;
    }


    //****Scheduler******************************
    // Question 2 : 2 minutes après la FIN de chaque exécution
    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.MINUTES)
    public void suivreNombreAgences() {
        List<Agence> agences = agenceRepository.findAll();
        log.info("[Suivi agences] Nombre total d'agences enregistrées : {}", agences.size());
        log.info("[Suivi agences] Date et heure de la vérification : {}", LocalDateTime.now().format(FORMAT));
    }


}