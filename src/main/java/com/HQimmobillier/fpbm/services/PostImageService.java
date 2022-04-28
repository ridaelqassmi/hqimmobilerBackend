package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.RentingPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostImageService {
    public void saveImages(MultipartFile[] file, RentingPost rentingPost1) throws IOException;
}
