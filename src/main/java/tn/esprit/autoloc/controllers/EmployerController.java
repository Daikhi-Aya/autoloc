package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Employer;
import tn.esprit.autoloc.services.IEmployerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("employer")
public class EmployerController {
    private final IEmployerService employerService;

    @PostMapping
    public Employer addEmployer(@RequestBody Employer employer) {
        return employerService.ajouterEmployer(employer);
    }

    @PutMapping
    public Employer updateEmployer(@RequestBody Employer employer) {
        return employerService.modifierEmployer(employer);
    }

    @GetMapping
    public List<Employer> getAll() {
        return employerService.consulterEmployers();
    }

    @GetMapping("/getById/{idEmployer}")
    public Employer getEmployerById(@PathVariable Long idEmployer) {
        return employerService.consulterEmployerById(idEmployer);
    }

    @DeleteMapping("/delete/{idEmployer}")
    public void deleteById(@PathVariable Long idEmployer) {
        employerService.supprimerEmployerById(idEmployer);
    }
}