package com.HQimmobillier.fpbm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
   /*
   private List<Long> categoriesList;
   private List<Double> prices;
   private List<Integer> nbRooms;
   private List<Float> area;
   private String title;
   private Long cities;

   private List<Long> features;
   private Boolean OrderByPrice;
   private Boolean orderByDate;
   private boolean asc;
   private int page;*/
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
   //2 means desc
   //1 means asc
   private int order = 2;
   private int page= 0;
}
