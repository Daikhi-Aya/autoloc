package tn.esprit.autoloc.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.EquipementRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class IVehiculeServiceImp implements IVehiculeService {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;


    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> consulterVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule consulterVehiculeById(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule).orElse(null);
    }

    @Override
    public void supprimerVehiculeById(Long idVehicule) {
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable : " + idEquipement));

        boolean dejaAffecte = vehicule.getEquipements().stream()
                .anyMatch(e -> e.getId() == equipement.getId());
        if (!dejaAffecte) {
            vehicule.getEquipements().add(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));

        boolean retire = vehicule.getEquipements().removeIf(e -> e.getId() == idEquipement);
        if (!retire) {
            throw new EntityNotFoundException(
                    "L'equipement " + idEquipement + " n'est pas affecte au vehicule " + idVehicule);
        }
        return vehiculeRepository.save(vehicule);
    }


    //************Scheduler**************************
    // Question 1 : démarre 5 s après le lancement, puis toutes les 60 s
    @Scheduled(initialDelay = 5, fixedRate = 60, timeUnit = TimeUnit.SECONDS)
    public void actualiserEtatVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        log.info("[Actualisation] Nombre total de véhicules enregistrés : {}", vehicules.size());
        log.info("[Actualisation] Date et heure : {}", LocalDateTime.now().format(FORMAT));
    }

    // Question 3 : tous les lundis à 09h00
    @Scheduled(cron = "0 0 9 * * MON")
    public void genererRapportVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        int total = vehicules.size();

        log.info("========== RAPPORT HEBDOMADAIRE DES VÉHICULES ==========");
        log.info("Nombre total de véhicules : {}", total);
        log.info("Date et heure de génération : {}", LocalDateTime.now().format(FORMAT));
        log.info("========================================================");
    }


}