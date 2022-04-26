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
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="Post_type")
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
    @Column
    private Date date;
    @Column
    private boolean isClosed;
    @ManyToOne
    private Cities cities;
    @OneToMany
    @JoinColumn(name = "post")
    private List<PostImages> images;
    @ManyToOne
    private Categories categories;
    @ManyToOne
    private User user;




    public Post(Long id, String title, String location, int numberRoom, boolean hasAppliance, String description, Date date, boolean isClosed, Cities cities, Categories categories) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.numberRoom = numberRoom;
        this.hasAppliance = hasAppliance;
        this.description = description;
        this.date = date;
        this.isClosed = isClosed;
        this.cities = cities;
        this.categories = categories;
    }
}
