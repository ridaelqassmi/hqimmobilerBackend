package com.HQimmobillier.fpbm.dto.post;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditPostDto {

    private Long id;
    private String title;
    private int numberRoom;
    private Long categorieId;
    private double price;
    private float area;
    private String  description;
    private int salleBain;


}
