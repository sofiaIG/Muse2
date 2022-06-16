package com.codeclan.example.Muse.components;

import com.codeclan.example.Muse.models.Comment;
import com.codeclan.example.Muse.models.Post;
import com.codeclan.example.Muse.models.User;
import com.codeclan.example.Muse.models.UserAuth;
import com.codeclan.example.Muse.repositories.CommentRepository;
import com.codeclan.example.Muse.repositories.PostRepository;
import com.codeclan.example.Muse.repositories.UserAuthRepository;
import com.codeclan.example.Muse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentReposiroty;

    @Autowired
    UserAuthRepository userAuthRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args){

        User user = new User("Sofia", "Ignatiadi", "sofiaIG", "Hello! Welcome to my profile. Feel free to have a look at my content!");
        User user1 = new User("Kate", "Anderson", "Kate39", "Hello! I here to meet other musicians!");
        UserAuth userAuth1 = new UserAuth("kate39@gmail.com");
        UserAuth userAuth = new UserAuth("sofiaig@gmail.com" );
        Collections.addAll(user.getFavouriteArtists(), "Mitski", "Japanese Breakfast", "Soccer Mommy",
                "Snail Mail", "Phoebe Bridgers");
        Collections.addAll(user.getCurrentlyListeningTo(), "Kendrick Lamar", "2Pac", "Melentini");
        userAuth.setPassword("test123");
        userAuth1.setPassword("test123");
        userAuth1.setUser(user1);
        user1.setUserAuth(userAuth1);
        user.setUserAuth(userAuth);
        userAuth.setUser(user);


        userRepository.save(user);
        userAuthRepository.save(userAuth);
        userRepository.save(user1);
        userAuthRepository.save(userAuth1);

        Post post = new Post("Looking for singer", "Have a look on my profile!", user);
        Post post1 = new Post("Looking for a drummer", "My and my friends want to start a band! Look at my profile to see my music preferences", user);
        postRepository.save(post);
        postRepository.save(post1);
        Comment comment = new Comment("I am a signer. Would totally be interested", post, user1 );
        commentReposiroty.save(comment);



    }
}
