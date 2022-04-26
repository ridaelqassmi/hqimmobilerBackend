package com.HQimmobillier.fpbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("RENT")
public class RentingPost extends Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String duree;
    @Column
    private float price;

    public RentingPost( String duree, float price) {

        this.duree = duree;
        this.price = price;
    }




}
