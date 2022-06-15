    package com.HQimmobillier.fpbm.controller;

    import com.HQimmobillier.fpbm.dto.user.FilterDto;
    import com.HQimmobillier.fpbm.entity.RentingPost;
    import com.HQimmobillier.fpbm.repository.RentingPostRepo;
    import com.HQimmobillier.fpbm.services.PostService;

    import org.springframework.context.annotation.Bean;
    import org.springframework.data.domain.Page;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.Arrays;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("api/")
    @CrossOrigin
    public class RentingPostController {

        private final PostService postService;

        private final RentingPostRepo rentingPostRepo;

        private final PostService rentingPostService;

        public RentingPostController(PostService postService, RentingPostRepo rentingPostRepo, PostService rentingPostService) {
            this.postService = postService;
            this.rentingPostRepo = rentingPostRepo;
            this.rentingPostService = rentingPostService;
        }


        @PostMapping("rent")
        public RentingPost createRentingPost(@RequestParam(value= "files",required = false) MultipartFile[] files
                ,@RequestParam(value = "idCity") long idCity,
                                             @RequestParam(value = "idCategorie") long idCategorie,
                                             @RequestParam(value = "rentingPost") String rentingPost) throws IOException {
            System.out.println("i am here "+ Arrays.toString(files));
            return rentingPostService.createRentingPost(idCity,idCategorie,files,rentingPost);

        }

        @GetMapping(value = {"/rent", "/rent/page/{id}"})
        public Page<RentingPost> findAll(@PathVariable(required = false) Integer id){
            if(id == null){
                return rentingPostService.getRentingPostWithPagination(0,20);
            }
            return rentingPostService.getRentingPostWithPagination(id,20);

        }

        @GetMapping("/rent/field/{field}")
        public Page<RentingPost>  findAllSortedd(@PathVariable(required=true,name = "field") String field,
                                                 @RequestParam(required=false,name = "sort") Optional<String> sort,
                                                 @RequestParam(required=true,name="page" ) Optional<Integer> page)
        {
            return rentingPostService.sortRentingPostBy(field,sort,page,20);
        }
        @GetMapping("/rent/categorie/{id}/page/{idPage}")
        public Page<RentingPost> getPostsByCategorie(@PathVariable(name = "idPage") int page,@PathVariable(name = "id") long id){
            return rentingPostService.getRentingPostByCategories(page,20,id);
        }

        @PutMapping("rent/{id}")
        public RentingPost update(@PathVariable long id, @RequestBody RentingPost rentingPost){
            RentingPost post = postService.findRentingPostById(id);
            post.setDuree(rentingPost.getDuree());
            post.setTitle(rentingPost.getTitle());

            post.setPrice(rentingPost.getPrice());
            post.setAreaSize(rentingPost.getAreaSize());
            post.setCategories(rentingPost.getCategories());
            post.setNumberRoom(rentingPost.getNumberRoom());
            return rentingPostRepo.save(post);

        }

       @GetMapping("rent/{id}")
        public RentingPost getRentingPostById(@PathVariable long id){
            return rentingPostRepo.findById(id).get();
       }

       @GetMapping("rent/filters/")
       public Page<RentingPost> getRentingPostByTitle(@PathVariable(value = "title") String title,
                                                      @PathVariable(value = "page",required = false) Integer page){
            if(page == null){
                return postService.getRentingPostByTitle(title,0);
            }
            return  postService.getRentingPostByTitle(title,page);

       }
       @PostMapping("rent/filter")
        public List<RentingPost> getRentingPostFilters(@RequestBody FilterDto filter){
            return postService.getRentingPostFilter(filter);
       }

       @GetMapping("/rent/search/by")

        public List<RentingPost> getRentingPostByTitle(@RequestParam(name="title") String title){
            title ="%"+title+"%";
            return rentingPostRepo.findByTitleLike(title);
       }
    }
