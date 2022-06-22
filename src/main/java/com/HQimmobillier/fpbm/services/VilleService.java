package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.Ville;

import java.util.List;

public interface VilleService {
    public List<Ville> findAll();
    public Ville createVille(Ville cities);
    public void deleteCity(Long id);
    public Ville update(Ville city, Long id);
    public Ville findById(Long id);

    List<Ville> getAllCitiesByRegion(int id);
}
