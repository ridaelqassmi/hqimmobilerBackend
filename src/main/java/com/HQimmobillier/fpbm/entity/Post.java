package com.HQimmobillier.fpbm.entity;

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
    private String location;
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
    private Cities cities;
    @OneToMany(mappedBy = "post")
    private List<PostImages> images;
    @ManyToOne
    private Categories categories;
    private double lat;
    private double lng;
    private String thumbnail;
    @ManyToOne
    private User user;
    @OneToMany
    private List<review> comments;

    @OneToOne
    private Etat etat;
    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : val.value();
    }
}
