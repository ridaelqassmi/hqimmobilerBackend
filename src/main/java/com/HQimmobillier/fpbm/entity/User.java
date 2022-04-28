package com.HQimmobillier.fpbm.entity;

import com.HQimmobillier.fpbm.utility.CommenFunctions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor @Data
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String LastName;
   /* @Column(unique=true)*/
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private boolean is_enabled;
    @Column
    private Date creation_date;

    @Column
    private String photos;
    private byte[] photosInBytes;

    public User( String firstName,
                 String lastName,
                 String email,
                 String phone,
                 boolean is_enabled,
                 Date creation_date,
                 String photos) {

        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.phone = phone;
        this.is_enabled = is_enabled;
        this.creation_date = creation_date;
        this.photos = photos;

    }

}

