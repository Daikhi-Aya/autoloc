package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Employer;

import java.util.List;

public interface IEmployerService {
    Employer ajouterEmployer(Employer employer);
    Employer modifierEmployer(Employer employer);
    List<Employer> consulterEmployers();
    Employer consulterEmployerById(Long idEmployer);
    void supprimerEmployerById(Long idEmployer);
}