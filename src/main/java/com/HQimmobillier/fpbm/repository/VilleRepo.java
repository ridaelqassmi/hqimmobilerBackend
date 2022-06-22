package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Region;
import com.HQimmobillier.fpbm.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilleRepo extends JpaRepository<Ville,Long> {


    List<Ville> findAllByRegion(Region region);
}
