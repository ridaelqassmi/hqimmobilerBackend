package com.HQimmobillier.fpbm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="Post_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = BuyingPost.class, name = "BUY"),
        @JsonSubTypes.Type(value = RentingPost.class, name = "RENT"),
})
public abstract class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private int numberRoom;
    @Column
    private boolean hasAppliance;
    @Column
    private String description;

    @Nullable
    @Column double price;
    @Column
    private Date date;
    @Column
    private boolean approved;
    @Column
    private float areaSize;
    @Column
    private boolean isClosed;
    @ManyToOne
    private Ville cities;
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)


    private List<PostImages> images;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Messages> messages;


    @ManyToOne
    private Categories categories;
    private double lat;
    private double lng;
    private String thumbnail;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
    private Date availableTill;

    @OneToMany
    private List<Features> features;
    private int SalleBain;
    private String adress;


    @OneToOne
    private Etat etat;
    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : val.value();
    }

    public Post(String title, int numberRoom, boolean hasAppliance, String description, double price, Date date, boolean approved, float areaSize, boolean isClosed, Ville cities, List<PostImages> images, Categories categories, double lat, double lng, String thumbnail, User user, List<Review> comments, Date availableTill, List<Features> features, int salleBain, String adress, Etat etat) {
        this.title = title;

        this.numberRoom = numberRoom;
        this.hasAppliance = hasAppliance;
        this.description = description;
        this.price = price;
        this.date = date;
        this.approved = approved;
        this.areaSize = areaSize;
        this.isClosed = isClosed;
        this.cities = cities;
        this.images = images;
        this.categories = categories;
        this.lat = lat;
        this.lng = lng;
        this.thumbnail = thumbnail;
        this.user = user;
        this.reviews = comments;
        this.availableTill = availableTill;
        this.features = features;
        SalleBain = salleBain;
        this.adress = adress;
        this.etat = etat;
    }
}
