package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.PostImages;
import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.repository.PostImagesRepo;
import com.HQimmobillier.fpbm.services.PostImageService;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostImagesServiceImp implements PostImageService {
    @Autowired
    PostImagesRepo postImagesRepo;
    public void saveImages(MultipartFile[] file, RentingPost rentingPost1) throws IOException {
        List<PostImages> postImages = new ArrayList<>();
        for (MultipartFile o : file) {
            String fileName = StringUtils.cleanPath(o.getOriginalFilename());
            PostImages postImage = new PostImages();
            postImage.setImageName(fileName);
            postImage.setPost(rentingPost1);

            String uploadDir = "images/postImages/rent/" + rentingPost1.getId() + "/" + rentingPost1.getId();
            postImage.setImagePath(uploadDir + "/" + fileName);

            CommenFunctions.saveFile(uploadDir, fileName, o);
            postImages.add(postImage);


        }
        postImagesRepo.saveAll(postImages);
    }
}
