package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.services.IContratService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("contrat")//identification lil path mtaana
public class ContratController {
    private final IContratService contratService;

    @PostMapping
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.ajouterContrat(contrat);
    }

    @PutMapping
    public Contrat updateContrat(@RequestBody Contrat contrat) {
        return contratService.modifierContrat(contrat);
    }

    @GetMapping
    public List<Contrat> getAll() {
        return contratService.consulterContrats();
    }

    @GetMapping("/getById/{idContrat}")
    public Contrat getContratById(@PathVariable Long idContrat) {
        return contratService.consulterContratById(idContrat);
    }

    @DeleteMapping("/delete/{idContrat}")
    public void deleteById(@PathVariable Long idContrat) {
        contratService.supprimerContratById(idContrat);
    }
}
