package com.HQimmobillier.fpbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false)
    private String categorieName;
    @Column
    private String categorieImage;
    @Column
    private String description;


    public Categories( String categorieName, String categorieImage, String description) {
        this.categorieName = categorieName;
        this.categorieImage = categorieImage;
        this.description = description;
    }

}
