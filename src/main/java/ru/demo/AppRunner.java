package ru.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.model.User;
import ru.demo.repository.UserRepository;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) {
        User u1 = User.builder().login("andrei").password("111111").username("andrei").build();
        User u2 = User.builder().login("ivan").password("000000").username("ivan").build();

        userRepository.save(u1);
        userRepository.save(u2);
    }

}