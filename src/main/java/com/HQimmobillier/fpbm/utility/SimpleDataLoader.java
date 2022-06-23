package com.HQimmobillier.fpbm.utility;

import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.repository.*;

import com.github.javafaker.Faker;


import org.springframework.boot.CommandLineRunner;


import java.util.ArrayList;
import java.util.List;


//@Component
public class SimpleDataLoader implements CommandLineRunner {

    private final BuyingPostRepo buyingPostRepo;
    private final Faker faker;
    private final CategoriesRepo categoriesRepo;
    private final EtatRepository etatRepository;
    private final RegionRepository regionRepository;
    private final RolesRepo rolesRepo;

    private final VilleRepo citiesRepo;

    private final UserRepo userRepo;

    RentingPostRepo rentingPostRepo;
    public SimpleDataLoader(BuyingPostRepo buyingPostRepo, Faker faker, CategoriesRepo categoriesRepo
            , EtatRepository etatRepository, RegionRepository regionRepository,
                            RolesRepo rolesRepo, VilleRepo citiesRepo, UserRepo userRepo) {
        this.buyingPostRepo = buyingPostRepo;
        this.faker = faker;
        this.categoriesRepo = categoriesRepo;
        this.etatRepository = etatRepository;
        this.regionRepository = regionRepository;
        this.rolesRepo = rolesRepo;
        this.citiesRepo = citiesRepo;
        this.userRepo = userRepo;
    }



    public void run(String... args) throws Exception {
        System.out.println("i am here ");

        //define a role
        List<Roles> UserRoles = new ArrayList<>();
        List<Roles> AdminRoles  = new ArrayList<>();
        Roles roles1 = new Roles(1,"ADMIN");
        Roles roles2 = new Roles(2,"USER");
        roles1 = rolesRepo.save(roles1);
        roles2 = rolesRepo.save(roles2);
        UserRoles.add(roles1);
        AdminRoles.add(roles1);
        AdminRoles.add(roles2);

        //create user
        List<User> users = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User(faker.name().firstName(),faker.name().lastName(),faker.internet().password(),faker.internet().emailAddress(),"" +faker.phoneNumber(),true,faker.date().birthday(),faker.name().firstName(),UserRoles) ;
            User user1= userRepo.save(user);
            users.add(user1);
        }

        List<Categories> categories = new ArrayList<>();
        String[] categoriesTab =  {"rida","appartement","Maisons","Villas & maisons de luxe","Bureaux","Terrains"};
        for(String e:categoriesTab){
            categories.add(categoriesRepo.save(new Categories(e)));
        }

        //adding etat
        List<Etat> etats = new ArrayList<>();
        String[] etatTab  = {"Nouveau","Bon état"};
        for(String e:etatTab){
            etats.add(etatRepository.save(new Etat(e)));
        }

        List<Region> regions = new ArrayList<>();

        regions.add(regionRepository.save(new Region("Casablanca-Settat"))) ;
        regions.add(regionRepository.save(new Region("Marrakech-Safi")));
        regions.add(regionRepository.save(new Region("Fès-Meknès")));
        regions.add(regionRepository.save(new Region("Tanger-Tétouan-Al Hoceïma")));
       regions.add(regionRepository.save(new Region("Rabat-Salé-Kénitra")));
        regions.add(regionRepository.save(new Region("Souss-Massa")));
       regions.add(regionRepository.save(new Region("Oriental")));
        regions.add(regionRepository.save(new Region("Laâyoune-Sakia El Hamra")));
        regions.add(regionRepository.save(new Region("Béni Mellal-Khénifra")));
        regions.add(regionRepository.save(new Region("Guelmim-Oued Noun")));
       regions.add(regionRepository.save(new Region("Drâa-Tafilalet")));
         regions.add(regionRepository.save(new Region("Dakhla-Oued Ed-Dahab")));

}}
