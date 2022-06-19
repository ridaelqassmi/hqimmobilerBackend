package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.dto.post.MyPostsResponseDto;
import com.HQimmobillier.fpbm.dto.user.FilterDto;
import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.mapper.PostMapper;
import com.HQimmobillier.fpbm.repository.*;
import com.HQimmobillier.fpbm.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service

public class PostServiceImp implements PostService {
    private final RentingPostRepo rentingPostRepo;
    private final BuyingPostRepo buyingPostRepo;
    private final CategorieService categorieService;
    private final CityService cityService;
    private final UserRepo userRepo;


    private final PostImagesRepo postImagesRepo;

    private final PostImageService postImageService;

    private final CategoriesRepo categoriesRepo;
    private final AccountService accountService;
    private final PostRepository postRepo;
    private final PostMapper postMapper;
    public PostServiceImp(RentingPostRepo rentingPostRepo, BuyingPostRepo buyingPostRepo, CategorieService categorieService, CityService cityService, UserRepo userRepo, PostImagesRepo postImagesRepo, PostImageService postImageService, CategoriesRepo categoriesRepo, AccountService accountService, PostRepository postRepo, PostMapper postMapper) {
        this.rentingPostRepo = rentingPostRepo;
        this.buyingPostRepo = buyingPostRepo;
        this.categorieService = categorieService;
        this.cityService = cityService;
        this.userRepo = userRepo;
        this.postImagesRepo = postImagesRepo;
        this.postImageService = postImageService;
        this.categoriesRepo = categoriesRepo;
        this.accountService = accountService;
        this.postRepo = postRepo;
        this.postMapper = postMapper;
    }

    /*********************************************RENTING POST SERVICES ****************************************/
    @Override
    public RentingPost createRentingPost(long id_city,
                                         long id_categorie,
                                         MultipartFile[] file,
                                         String RentingPost1) throws IOException {

        RentingPost rentingPost = new ObjectMapper().readValue(RentingPost1, RentingPost.class);
        rentingPost.setCities(cityService.findById(id_city));
        rentingPost.setUser(accountService.getAuthenticatedUser());
        RentingPost rentingPost1 = rentingPostRepo.save(rentingPost);
        postImageService.saveImages(file, rentingPost1);


        return rentingPost1;
    }

    public RentingPost findRentingPostById(long id) {
        if (rentingPostRepo.findById(id).isPresent()) {
            return rentingPostRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public List<RentingPost> getAllRentingPosts(String title, Integer page) {
        return rentingPostRepo.findAll();
    }



    /*SORT POSTS BY ANY FIELD */

    public Page<RentingPost> sortRentingPostBy(String field, Optional<String> typeOfSort, Optional<Integer> page, int size) {
        int p;
        p = page.orElse(0);
        Pageable sorted;
        if (typeOfSort.isEmpty()) {

            sorted = PageRequest.of(p, size, Sort.by(field).ascending());

        } else {
            sorted = PageRequest.of(p, size, Sort.by(field).descending());
        }
        return rentingPostRepo.findAll(sorted);

    }

    /*find all Posts with pagination */
    public Page<RentingPost> getRentingPostWithPagination(int page, int size) {
        return rentingPostRepo.findAll(PageRequest.of(page, size));
    }

    public List<RentingPost> getAllRentigPostByUserId(long id) {
        User user = userRepo.findById(id).get();
        if (user != null) {
            return rentingPostRepo.findAllRentingPostsByUser(user);
        }
        return null;
    }

    public Page<RentingPost> getRentingPostByCategories(int page, int size, long id) {
        Pageable pageRequest = PageRequest.of(page, size);
        Categories categories = categoriesRepo.findById(id).get();
        return rentingPostRepo.findAllByCategories(categories, pageRequest);
    }


    public Page<RentingPost> getRentingPostByTitle(String title, int page) {
        Pageable pageRequest = PageRequest.of(page, 20);
        return rentingPostRepo.findByTitleContaining(title, pageRequest);
    }

    public Page<RentingPost> getRentingPostByFilter(FilterDto filterDto) {
        Pageable pageRequest = PageRequest.of(filterDto.getPage(), 20);
        return rentingPostRepo.findByCostumeFilter(filterDto.getTitle(), filterDto.getCities(), filterDto.getCategoriesList(), filterDto.getPrices().get(0), filterDto.getPrices().get(1), filterDto.getNbRooms().get(0), filterDto.getNbRooms().get(1), filterDto.getArea().get(0), filterDto.getArea().get(1), filterDto.getOrderByDate(), filterDto.getOrderByPrice(), filterDto.isAsc(), pageRequest);


    }

    @Override
    public List<MyPostsResponseDto> findAllPostOfAuthentifiedUser() {
        List<Post> posts = postRepo.findAllPostsByUser(accountService.getAuthenticatedUser());
        return postMapper.MapPostToMyPostResponseDto(posts);
    }
    /************************************************BUYING POST SERVICES ********************************************/


}
