package com.HQimmobillier.fpbm.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
 @Data
@AllArgsConstructor
@NoArgsConstructor
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageSrc;

    public Features(String name, String imageSrc) {
        this.name = name;
        this.imageSrc = imageSrc;
    }
}
