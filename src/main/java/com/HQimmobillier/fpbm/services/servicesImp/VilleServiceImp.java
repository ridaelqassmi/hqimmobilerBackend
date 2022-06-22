package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.Region;
import com.HQimmobillier.fpbm.entity.Ville;
import com.HQimmobillier.fpbm.repository.RegionRepository;
import com.HQimmobillier.fpbm.repository.VilleRepo;
import com.HQimmobillier.fpbm.services.VilleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VilleServiceImp implements VilleService {

    private final VilleRepo villeRepo;
    private final RegionRepository regionRepository;

    public VilleServiceImp(VilleRepo citiesRepo, RegionRepository regionRepository) {
        this.villeRepo = citiesRepo;
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Ville> findAll() {
        return villeRepo.findAll();
    }

    @Override
    public Ville createVille(Ville cities) {
        return villeRepo.save(cities);
    }

    @Override
    public void deleteCity(Long id) {
        villeRepo.deleteById(id);
    }

    @Override
    public Ville update(Ville city, Long id) {
        city.setId(id);
        return villeRepo.save(city);
    }

    @Override
    public Ville findById(Long id) {
        return villeRepo.findById(id).get();
    }

    @Override
    public List<Ville> getAllCitiesByRegion(int id) {
        Region region = regionRepository.findById(id).get();
        return villeRepo.findAllByRegion(region);
    }
}
