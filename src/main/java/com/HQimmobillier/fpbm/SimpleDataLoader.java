package com.HQimmobillier.fpbm;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.entity.Cities;
import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.BuyingPostRepo;
import com.HQimmobillier.fpbm.repository.CategoriesRepo;
import com.HQimmobillier.fpbm.repository.CitiesRepo;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleDataLoader implements CommandLineRunner {
    @Autowired
    private final BuyingPostRepo buyingPostRepo;
    private final Faker faker;
    @Autowired final CategoriesRepo categoriesRepo;
    @Autowired
    CitiesRepo citiesRepo;
    @Autowired
    UserRepo userRepo;

    public SimpleDataLoader(BuyingPostRepo buyingPostRepo, Faker faker, CategoriesRepo categoriesRepo) {
        this.buyingPostRepo = buyingPostRepo;
        this.faker = faker;
        this.categoriesRepo = categoriesRepo;
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
        for(int i = 0;i<100;i++){
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






    }





}
