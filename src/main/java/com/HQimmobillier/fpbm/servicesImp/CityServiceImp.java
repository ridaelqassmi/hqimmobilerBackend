package com.HQimmobillier.fpbm.servicesImp;

import com.HQimmobillier.fpbm.entity.Cities;
import com.HQimmobillier.fpbm.repository.CitiesRepo;
import com.HQimmobillier.fpbm.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityServiceImp implements CityService {
    @Autowired
    CitiesRepo citiesRepo;
    @Override
    public List<Cities> findAll() {
        return citiesRepo.findAll();
    }

    @Override
    public Cities createCity(Cities cities) {
        return citiesRepo.save(cities);
    }

    @Override
    public void deleteCity(Long id) {
        citiesRepo.deleteById(id);
    }

    @Override
    public Cities update(Cities city, Long id) {
        city.setId(id);
        return citiesRepo.save(city);
    }

    @Override
    public Cities findById(Long id) {
        return citiesRepo.findById(id).get();
    }
}
