package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.PostImages;
import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.repository.PostImagesRepo;
import com.HQimmobillier.fpbm.services.PostImageService;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.HQimmobillier.fpbm.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostImagesServiceImp implements PostImageService {

private final PostImagesRepo postImagesRepo;

public PostImagesServiceImp(PostImagesRepo postImagesRepo) {
    this.postImagesRepo = postImagesRepo;
}

public void saveImages(MultipartFile[] file, RentingPost rentingPost1) throws IOException {
    List<PostImages> postImages = new ArrayList<>();
    for (MultipartFile o : file) {

        PostImages postImage = new PostImages();

        postImage.setPost(rentingPost1);

        String uploadDir =  + rentingPost1.getId() + "/";

        String ImagePath = CommenFunctions.saveFile(Constants.RentingPostPath +uploadDir, o);
        postImage.setImagePath(ImagePath);
        postImages.add(postImage);
    }
    postImagesRepo.saveAll(postImages);
}
}
