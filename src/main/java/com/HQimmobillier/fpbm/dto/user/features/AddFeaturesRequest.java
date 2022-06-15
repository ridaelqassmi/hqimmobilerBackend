package com.HQimmobillier.fpbm.dto.user.features;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddFeaturesRequest {
    public MultipartFile file;
    public String name;
}
