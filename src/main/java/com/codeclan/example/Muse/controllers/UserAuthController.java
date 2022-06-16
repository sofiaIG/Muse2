package com.codeclan.example.Muse.controllers;

import com.codeclan.example.Muse.dtos.LogInDTO;
import com.codeclan.example.Muse.models.Post;
import com.codeclan.example.Muse.dtos.RegisterBodyDTO;
import com.codeclan.example.Muse.models.User;
import com.codeclan.example.Muse.models.UserAuth;
import com.codeclan.example.Muse.repositories.UserAuthRepository;
import com.codeclan.example.Muse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAuthController {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/userAuth")
    public ResponseEntity<List<UserAuth>> getAllUserAuth(){
        UserAuth testUser = userAuthRepository.findAll().get(0);
        System.out.println(testUser.getUser());
        return new ResponseEntity<>(userAuthRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RegisterBodyDTO registerBodyDTO){
        String firstName = registerBodyDTO.getFirstName();
        String lastName = registerBodyDTO.getLastName();
        String username = registerBodyDTO.getUsername();
        String email = registerBodyDTO.getEmail();
        String password = registerBodyDTO.getPassword();
        UserAuth userAuth = new UserAuth(email);
        User user = new User(firstName, lastName, username, "");
        user.setUserAuth(userAuth);
        userAuth.setPassword(password);
        userAuth.setUser(user);
        userRepository.save(user);
        userAuthRepository.save(userAuth);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LogInDTO logInDTO){
        String email = logInDTO.getEmail();
        String password = logInDTO.getPassword();
        List<UserAuth> users = userAuthRepository.findAll();

        for (UserAuth user: users) {
            if (user.getEmail().equals(email) && user.checkPassword(password)){
                return new ResponseEntity<>(user.getId(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
