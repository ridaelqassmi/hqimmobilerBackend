package com.HQimmobillier.fpbm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data

@NoArgsConstructor
@Entity
public class Cities {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cityName;
    @Column
    private String city_image;


    public Cities(String cityName, String city_image) {

        this.cityName = cityName;
        this.city_image = city_image;
    }

    public Cities(Long id, String cityName, String city_image) {
        this.id = id;
        this.cityName = cityName;
        this.city_image = city_image;

    }
}
