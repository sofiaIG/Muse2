package com.codeclan.example.Muse.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Table(name = "auth_user_details")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_KEY")
    private String password;

    @Column(name = "email", unique = true)
    private String email;


    @JsonIgnoreProperties({"auth_user_details"})
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "userAuth")
    private User user;


    public UserAuth(String email) {
        this.email = email;
    }

    public UserAuth() {
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean checkPassword(String plainTextPassword) {
        return BCrypt.checkpw(plainTextPassword, this.password);
    }
}