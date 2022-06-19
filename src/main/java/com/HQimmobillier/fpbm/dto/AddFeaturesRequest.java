package com.HQimmobillier.fpbm.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddFeaturesRequest {
    public MultipartFile file;
    public String name;
}
