package com.HQimmobillier.fpbm.dto.user;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.entity.Cities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
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
   private int page;
}
