package com.HQimmobillier.fpbm;

import com.HQimmobillier.fpbm.controller.userController;
import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.entity.Etat;
import com.HQimmobillier.fpbm.entity.Roles;
import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.*;
import com.github.javafaker.Faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication

public class FpbmApplication {


	@Bean
	Faker faker() {
		return new Faker();
	}
	public static void main(String[] args) {
		SpringApplication.run(FpbmApplication.class, args);


	}
/*
	@Bean
	public CommandLineRunner runner(SimpleDataLoader simpleDataLoader) {
		return args -> {
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

		};
	}


	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}




*/

}
