package com.HQimmobillier.fpbm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private float areaSize;
    @Column
    private boolean isClosed;
    @ManyToOne
    private Cities cities;
    @OneToMany(mappedBy = "post")
    private List<PostImages> images;
    @ManyToOne
    private Categories categories;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Comments> comments;
    public Post(String title, String location, int numberRoom, boolean hasAppliance, String description, Date date, float areaSize, boolean isClosed, Cities cities, List<PostImages> images, Categories categories, User user) {
        this.title = title;
        this.location = location;
        this.numberRoom = numberRoom;
        this.hasAppliance = hasAppliance;
        this.description = description;
        this.date = date;
        this.areaSize = areaSize;
        this.isClosed = isClosed;
        this.cities = cities;
        this.images = images;
        this.categories = categories;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public boolean isHasAppliance() {
        return hasAppliance;
    }

    public void setHasAppliance(boolean hasAppliance) {
        this.hasAppliance = hasAppliance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(float areaSize) {
        this.areaSize = areaSize;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }

    public List<PostImages> getImages() {
        return images;
    }

    public void setImages(List<PostImages> images) {
        this.images = images;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
