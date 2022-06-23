package com.HQimmobillier.fpbm.entity;

import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String Password;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> userRoles = new ArrayList<>();




    public User(String firstName, String lastName, String password, String email, String phone, boolean is_enabled, Date creation_date, String photos, List<Roles> userRoles) {
        this.firstName = firstName;
        LastName = lastName;
        Password = password;
        this.email = email;
        this.phone = phone;
        this.is_enabled = is_enabled;
        this.creation_date = creation_date;
        this.photos = photos;
        this.userRoles = userRoles;
    }




}

