package tn.esprit.autoloc.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Employer;
import tn.esprit.autoloc.repositories.EmployerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IEmployerServiceImp implements IEmployerService {

    private final EmployerRepository employerRepository;

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
}