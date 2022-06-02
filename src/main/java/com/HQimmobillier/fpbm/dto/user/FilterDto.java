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
   private List<Float> prices;
   private List<Integer> nbRooms;
   private List<Float> area;
   private List<Long> cities;
   private boolean orderByPrice;
}
