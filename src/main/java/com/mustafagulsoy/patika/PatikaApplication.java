package com.mustafagulsoy.patika;

import com.mustafagulsoy.patika.entity.Role;
import com.mustafagulsoy.patika.entity.User;
import com.mustafagulsoy.patika.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class PatikaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatikaApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            int max = 2000;
            int min = 0;

            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN")); //  İstek üzerine farklı roller proje ayaktayken yüklenebilir
//            userService.saveRole(new Role(null, "ROLE_MANAGER" ));
//            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(111111L, "Mustafa", "Gulsoy", 5000, "5301111111", (new Date()).toString(), (int) ((Math.random() * (max - min)) + min), (int) ((Math.random() * (max - min)) + min), "mustafagulsoy", "1234", 0, new ArrayList<>()));
            userService.saveUser(new User(111112L, "Patika", "Backend", 5000, "5301111112", (new Date()).toString(), (int) ((Math.random() * (max - min)) + min), (int) ((Math.random() * (max - min)) + min), "patikaBack", "1234", 0, new ArrayList<>()));
            userService.saveUser(new User(111113L, "Patika", "FrontEnd", 5000, "5301111113", (new Date()).toString(), (int) ((Math.random() * (max - min)) + min), (int) ((Math.random() * (max - min)) + min), "patikaFront", "1234", 0, new ArrayList<>()));
            userService.saveUser(new User(111114L, "Patika", "Devop", 5000, "5301111114", (new Date()).toString(), (int) ((Math.random() * (max - min)) + min), (int) ((Math.random() * (max - min)) + min), "patikaDevop", "1234", 0, new ArrayList<>()));

            userService.addRoleToUser("mustafagulsoy", "ROLE_USER");
            userService.addRoleToUser("patikaBack", "ROLE_USER");
            userService.addRoleToUser("patikaFront", "ROLE_USER");
            userService.addRoleToUser("patikaDevop", "ROLE_USER");

        };
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
