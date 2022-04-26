package com.HQimmobillier.fpbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class PostImages{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imageName;
    @Column
    private String imagePath;

    public PostImages(Long id, String imageName) {
        this.id = id;
        this.imageName = imageName;
        //this.imagePath = imagePath;
    }
}
