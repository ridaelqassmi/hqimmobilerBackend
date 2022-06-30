package com.HQimmobillier.fpbm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne

    private User user;
    private String content;
    @Column(columnDefinition="double(2,1)")

    private double rating;
    @Column
    private Timestamp createdAt;

    @ManyToOne


    private Post post;

    public Review(User user, String content, double rating, Timestamp createdAt, Post post) {
        this.user = user;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.post = post;
    }
}
