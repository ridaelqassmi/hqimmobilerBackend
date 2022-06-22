package com.HQimmobillier.fpbm.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDto implements Serializable {
    private int type ;
    private String title;
    private float surfacie;
    private double prix;
    private long categorie;
    private int salleBain;
    private Date dateAvailabilty;
    private String description;
    private int duree;
    private int numberRoom;
    private int etat;
    private int region;

   // private MultipartFile[] images;

    private long ville;
    private String address;
    private double lat;
    private double lng;
    private List<Long> features;

}
