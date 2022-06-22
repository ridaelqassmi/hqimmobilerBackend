package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Etat;
import com.HQimmobillier.fpbm.repository.EtatRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EtatController {

    private final EtatRepository etatRepository;

    public EtatController(EtatRepository etatRepository) {
        this.etatRepository = etatRepository;
    }

    @GetMapping("api/etat")
    public List<Etat> getAllEtat(){
        return etatRepository.findAll();
    }

}
