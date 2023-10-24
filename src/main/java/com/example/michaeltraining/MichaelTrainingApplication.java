package com.example.michaeltraining;

import com.example.michaeltraining.user.User;
import com.example.michaeltraining.user.UserRepository;
import com.example.michaeltraining.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@SpringBootApplication
public class MichaelTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MichaelTrainingApplication.class, args);
    }

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void fixture() {
        if (userRepository.count() == 0) {
            Arrays.asList(
                    new User.NewUserProjection("John", "Dosen", 32L),
                    new User.NewUserProjection("Peter", "Stone", 12L),
                    new User.NewUserProjection("Jessica", "Morten", 57L),
                    new User.NewUserProjection("Gwendoline", "Sorington", 61L),
                    new User.NewUserProjection("Velar", "Tusk", 89L)
            ).forEach(userService::saveUser);
        }
    }
}
