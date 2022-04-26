package com.HQimmobillier.fpbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("BUY")
public class buyingPost extends Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float price;

    public buyingPost( float price) {
        this.price = price;
    }

    public buyingPost(Long id, String title, String location, int numberRoom, boolean hasAppliance, String description, Date date, boolean isClosed, Cities cities, Categories categories, Long id1, float price) {
        super(id, title, location, numberRoom, hasAppliance, description, date, isClosed, cities, categories);
        this.id = id1;
        this.price = price;
    }
}
