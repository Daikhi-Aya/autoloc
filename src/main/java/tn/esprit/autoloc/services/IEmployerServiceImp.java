package tn.esprit.autoloc.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.entities.Employer;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.repositories.EmployerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IEmployerServiceImp implements IEmployerService {
    private final EmployerRepository employerRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public Employer ajouterEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public Employer modifierEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public List<Employer> consulterEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Employer consulterEmployerById(Long idEmployer) {
        return employerRepository.findById(idEmployer).orElse(null);
    }

    @Override
    public void supprimerEmployerById(Long idEmployer) {
        employerRepository.deleteById(idEmployer);
    }

    @Override
    @Transactional
    public Employer desaffecterEmployer(Long idEmployer) {
        Employer employer = employerRepository.findById(idEmployer)
                .orElseThrow(() -> new EntityNotFoundException("Employer introuvable : " + idEmployer));
        employer.setAgence(null);
        return employerRepository.save(employer);
    }

    @Override
    @Transactional
    public Employer affecterEmployer(Long idEmployer, Long idAgence) {
        Employer employer = employerRepository.findById(idEmployer)
                .orElseThrow(() -> new EntityNotFoundException("Employer introuvable : " + idEmployer));
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : " + idAgence));
        employer.setAgence(agence);
        return employerRepository.save(employer);
    }
}