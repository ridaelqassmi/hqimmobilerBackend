package com.HQimmobillier.fpbm;

import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*@Component*/
public class SimpleDataLoader implements CommandLineRunner {
    @Autowired
    private final BuyingPostRepo buyingPostRepo;
    private final Faker faker;
    @Autowired final CategoriesRepo categoriesRepo;
    @Autowired
    CitiesRepo citiesRepo;
    @Autowired
    UserRepo userRepo;
@Autowired
RentingPostRepo rentingPostRepo;
    public SimpleDataLoader(BuyingPostRepo buyingPostRepo, Faker faker, CategoriesRepo categoriesRepo) {
        this.buyingPostRepo = buyingPostRepo;
        this.faker = faker;
        this.categoriesRepo = categoriesRepo;
    }

    public List<PostImages> generateAListOfImages(){
        int i = 0;
        List<PostImages> images = new ArrayList<>();
        while(i<8){
            PostImages img = new PostImages(
                    faker.name().title(),
                    faker.internet().image()
            );
            images.add(img);

            i++;
        }
        return images;
    }

    @Override

    public void run(String... args) throws Exception {



    List<Categories> categories = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Categories c  = new Categories(
                    faker.commerce().department(),
                    faker.commerce().department(),
                    faker.commerce().department()
            );
            categories.add(c);
        }

        categoriesRepo.saveAll(categories);

        List<Cities> cities = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Cities c  = new Cities(faker.address().cityName(), faker.avatar().image());
            cities.add(c);
        }

        citiesRepo.saveAll(cities);
        List<User> users = new ArrayList<>();
        for(int i=0;i<100;i++){
            User user = new User(faker.name().firstName(),
                    faker.name().lastName(),
                    " "+ faker.name().hashCode(),
                    faker.name().firstName()+"@gmail.com",
                    faker.phoneNumber().phoneNumber(),
                    false,
                    faker.date().birthday(),
                    faker.avatar().image());
            users.add(user);
        };
       userRepo.saveAll(users);


       List<RentingPost> rentingPost = new ArrayList<>();
       List<Categories> categorie = categoriesRepo.findAll();
       List<Cities> city = citiesRepo.findAll();
       List<User> user = userRepo.findAll();
        Random rn = new Random();
        Categories ct = new Categories();
        Cities c = new Cities();
        User u = new User();
       for(int i=0;i<1000;i++){
          int categoriePlace =rn.nextInt(categorie.size() -1);
          ct = categoriesRepo.findById(categorie.get(categoriePlace).getId()).get();

           int citiesPlace =rn.nextInt(city.size() -1);
           c = citiesRepo.findById(city.get(citiesPlace).getId()).get();

           int userPlace =rn.nextInt(user.size() -1);
           u = userRepo.findById(user.get(userPlace).getId()).get();

           RentingPost renn = new RentingPost(
                   faker.name().title(),
                   faker.address().streetAddress(),
                   faker.number().numberBetween(2,20),
                   false,
                   faker.gameOfThrones().quote(),
                   faker.date().birthday(),
                   100.55f,
                   false,
                   c,
                   generateAListOfImages(),
                   ct,
                   u,
                   "week",
                   faker.number().numberBetween(150,1000)
           );
           rentingPost.add(renn);

       }
       rentingPostRepo.saveAll(rentingPost);

    }





}
