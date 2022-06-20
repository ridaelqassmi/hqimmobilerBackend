package com.HQimmobillier.fpbm.dto.post;

import lombok.Data;

import java.util.Date;

@Data
public class CreatePostDto {
    private int type ;
    private String title;
    private double surfacie;
    private double prix;
    private long categorie;
    private int salleBain;
    private Date dateAvailabilty;
    private String description;
    private int duree;
    private int numberRoom;
    private int etat;
}
