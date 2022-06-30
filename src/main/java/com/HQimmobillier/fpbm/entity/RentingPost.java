package com.HQimmobillier.fpbm.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("RENT")
public class RentingPost extends Post{
    @OneToOne
    private Duree duree;
    public Duree getDuree() {
        return duree;
    }
    public void setDuree(Duree duree) {
        this.duree = duree;
    }

    public RentingPost(String title, int numberRoom, boolean hasAppliance, String description, double price, Date date, boolean approved, float areaSize, boolean isClosed, Ville cities, List<PostImages> images, Categories categories, double lat, double lng, String thumbnail, User user, List<Review> comments, Date availableTill, List<Features> features, int salleBain, String adress, Etat etat, Duree duree) {
        super(title,numberRoom, hasAppliance, description, price, date, approved, areaSize, isClosed, cities, images, categories, lat, lng, thumbnail, user, comments, availableTill, features, salleBain, adress, etat);
        this.duree = duree;
    }
}
