package com.codeclan.example.Muse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "favorite_artists")
    @Convert(converter = ListToStringConverter.class)
    private List<String> favouriteArtists;

    @Column(name = "currently_listening_to")
    @Convert(converter = ListToStringConverter.class)
    private List<String> currentlyListeningTo;

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;

    @Column(name = "bio")
    private String bio;


    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_auth_id", referencedColumnName = "id")
    private UserAuth userAuth;


    public User(String firstName, String lastName, String userName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.posts = new ArrayList<>();
        this.favouriteArtists = new ArrayList<>();
        this.currentlyListeningTo = new ArrayList<>();
        this.bio = bio;
    }

    public User() {
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getCurrentlyListeningTo() {
        return currentlyListeningTo;
    }

    public void setCurrentlyListeningTo(List<String> currentlyListeningTo) {
        this.currentlyListeningTo = currentlyListeningTo;
    }

    public List<String> getFavouriteArtists() {
        return favouriteArtists;
    }

    public void setFavouriteArtists(List<String> favouriteArtists) {
        this.favouriteArtists = favouriteArtists;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }



    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Post> getPosts() {
        return posts;
    }

}
