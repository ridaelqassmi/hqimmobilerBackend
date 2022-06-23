package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.dto.post.CreatePostDto;
import com.HQimmobillier.fpbm.dto.post.MyPostsResponseDto;
import com.HQimmobillier.fpbm.dto.user.FilterDto;
import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.mapper.PostMapper;
import com.HQimmobillier.fpbm.repository.*;
import com.HQimmobillier.fpbm.services.*;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.HQimmobillier.fpbm.utility.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class PostServiceImp implements PostService {
    private final RentingPostRepo rentingPostRepo;
    private final BuyingPostRepo buyingPostRepo;
    private final CategorieService categorieService;
    private final VilleService cityService;
    private final UserRepo userRepo;


    private final PostImagesRepo postImagesRepo;

    private final PostImageService postImageService;

    private final CategoriesRepo categoriesRepo;
    private final AccountService accountService;
    private final PostRepository postRepo;
    private final PostMapper postMapper;
    private final DureeRepository dureeRepository;
    private final FeaturesRepo featuresRepo;
    private final EtatRepository etatRepository;
    public PostServiceImp(RentingPostRepo rentingPostRepo, BuyingPostRepo buyingPostRepo, CategorieService categorieService, VilleService cityService, UserRepo userRepo, PostImagesRepo postImagesRepo, PostImageService postImageService, CategoriesRepo categoriesRepo, AccountService accountService, PostRepository postRepo, PostMapper postMapper, DureeRepository dureeRepository, FeaturesRepo featuresRepo, EtatRepository etatRepository) {
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
        this.dureeRepository = dureeRepository;
        this.featuresRepo = featuresRepo;

        this.etatRepository = etatRepository;
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
        Pageable pageRequest;

        if(filterDto.getOrder() == 1){
           pageRequest = PageRequest.of(filterDto.getPage(), 20,Sort.by(filterDto.getField()).ascending());
        }
        else{
            pageRequest = PageRequest.of(filterDto.getPage(), 20,Sort.by(filterDto.getField()).descending());
        }



        return rentingPostRepo.findByCostumeFilter(filterDto.getTitle(), filterDto.getCityId(), filterDto.getCategorieId(), filterDto.getPriceMin(), filterDto.getPriceMax(), filterDto.getRoomMin(), filterDto.getRoomMax(), filterDto.getAreaMin(), filterDto.getAreaMax(), pageRequest);

    }

    @Override
    public List<MyPostsResponseDto> findAllPostOfAuthentifiedUser() {
        List<Post> posts = postRepo.findAllPostsByUser(accountService.getAuthenticatedUser());
        return postMapper.MapPostToMyPostResponseDto(posts);
    }


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
    @Override
    public Post savePost(MultipartFile thumbnail, MultipartFile[] images, String post) throws IOException {

        CreatePostDto createPostDto = new ObjectMapper().readValue(post,CreatePostDto.class);
        if(createPostDto.getType() == 2){
            return saveRentingPost(thumbnail, images, createPostDto);
        }else if(createPostDto.getType()==1){
            return saveBuyingPost(thumbnail, images, createPostDto);
        }
        return null;




    }

    private Post saveBuyingPost(MultipartFile thumbnail, MultipartFile[] images, CreatePostDto createPostDto) throws IOException {
        BuyingPost post1 = new BuyingPost();

        post1.setCities(cityService.findById(createPostDto.getVille()));
        post1.setCategories(categorieService.findById(createPostDto.getCategorie()));
        post1.setAreaSize( createPostDto.getSurfacie());
        post1.setUser(accountService.getAuthenticatedUser());
        post1.setApproved(false);
        post1.setLat(createPostDto.getLat());
        post1.setLng(createPostDto.getLng());
        post1.setClosed(false);
        post1.setDate(new Date());
        post1.setEtat(etatRepository.findById(createPostDto.getEtat()).get());
        post1.setDescription(createPostDto.getDescription());
        post1.setAvailableTill(createPostDto.getDateAvailabilty());


        if(createPostDto.getFeatures()!=null){
            List<Features> features = new ArrayList<>();
            for(Long e:createPostDto.getFeatures()){
                features.add(featuresRepo.findById(e).get());

            }
            post1.setFeatures(features);
        }


        post1.setTitle(createPostDto.getTitle());
        post1.setNumberRoom(createPostDto.getNumberRoom());
        post1.setSalleBain(createPostDto.getSalleBain());
        post1.setPrice(createPostDto.getPrix());
        post1.setAdress(createPostDto.getAddress());

        String Thumbnail = CommenFunctions.saveFile(Constants.thumbnailDir,thumbnail);
        post1.setThumbnail(Thumbnail);

        BuyingPost p = buyingPostRepo.save(post1);
        postImageService.saveImages(images, p);

        return p;

    }

    public Post saveRentingPost(MultipartFile thumbnail,MultipartFile[] images,CreatePostDto createPostDto) throws IOException {
        RentingPost post1 = new RentingPost();
        post1.setDuree(dureeRepository.findById(createPostDto.getDuree()).get());
        post1.setCities(cityService.findById(createPostDto.getVille()));
        post1.setCategories(categorieService.findById(createPostDto.getCategorie()));
        post1.setAreaSize( createPostDto.getSurfacie());
        post1.setUser(accountService.getAuthenticatedUser());
        post1.setApproved(false);
        post1.setLat(createPostDto.getLat());
        post1.setLng(createPostDto.getLng());
        post1.setClosed(false);
        post1.setDate(new Date());
        post1.setEtat(etatRepository.findById(createPostDto.getEtat()).get());
        post1.setDescription(createPostDto.getDescription());
        post1.setAvailableTill(createPostDto.getDateAvailabilty());


        if(createPostDto.getFeatures()!=null){
            List<Features> features = new ArrayList<>();
            for(Long e:createPostDto.getFeatures()){
                features.add(featuresRepo.findById(e).get());

            }
            post1.setFeatures(features);
        }


        post1.setTitle(createPostDto.getTitle());
        post1.setNumberRoom(createPostDto.getNumberRoom());
        post1.setSalleBain(createPostDto.getSalleBain());
        post1.setPrice(createPostDto.getPrix());
        post1.setAdress(createPostDto.getAddress());

        String Thumbnail = CommenFunctions.saveFile(Constants.thumbnailDir,thumbnail);
        post1.setThumbnail(Thumbnail);

        RentingPost p = rentingPostRepo.save(post1);
        postImageService.saveImages(images, p);

        return p;


    }


}
