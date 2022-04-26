package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.runtime.ObjectMethods;
import java.util.List;

public interface RentingPostService {


    public List<RentingPost> findAll();
    public RentingPost create(long categorie_id,long cities_id,String rentingPost) throws JsonProcessingException;

}
