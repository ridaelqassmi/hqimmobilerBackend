package com.HQimmobillier.fpbm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Post post;

    public PostImages(Long id, String imageName) {
        this.id = id;
        this.imageName = imageName;
        //this.imagePath = imagePath;
    }
}
