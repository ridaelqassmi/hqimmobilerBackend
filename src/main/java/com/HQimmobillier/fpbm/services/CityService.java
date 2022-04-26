package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.Cities;

import java.util.List;

public interface CityService {
    public List<Cities> findAll();
    public Cities createCity(Cities cities);
    public void deleteCity(Long id);
    public Cities update(Cities city,Long id);
    public Cities findById(Long id);
}
