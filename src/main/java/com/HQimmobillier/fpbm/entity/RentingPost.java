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

    public RentingPost(String title, String streetAddress, int numberBetween, boolean b, String quote, Date birthday, float v, boolean b1, Cities c, List<PostImages> generateAListOfImages, Categories ct, User u, String week, int numberBetween1) {
        super(title, streetAddress, numberBetween, b, quote,birthday, v, b1, c,generateAListOfImages, ct, u);
        this.duree = week;
        this.price = numberBetween1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
