package com.HQimmobillier.fpbm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
   /*1 for ventre
   * 2 for location
   * */
   private int type;

   private long categorieId;
   private double priceMin = 0;
   private double priceMax = 0;
   private int roomMin =0;
   private int roomMax =0;
   private float AreaMin = 0;
   private float AreaMax=0;
   private Long CityId=0L;
   private String field = "date";
   private String title = "";

   private int order = 2;
   private int page= 0;
}
