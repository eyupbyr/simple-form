package com.simpleform.service;

import com.simpleform.model.UsersModel;
import com.simpleform.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String username, String password, String email) {
        if (username == null || password == null) {
            return null;
        } else {
            if(usersRepository.findFirstByUsername(username).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setUsername(username);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public void makeApplicationWait() throws InterruptedException {
        Random rand = new Random();
        int randomInt = rand.nextInt(10);
        System.out.println("ilk randomint: " + randomInt);
        if(randomInt < 5) {
            randomInt = rand.nextInt(5) + 5;
            System.out.println("ikinci randomint: " + randomInt);
            Thread.sleep(randomInt * 1000);
        }
    }
}
