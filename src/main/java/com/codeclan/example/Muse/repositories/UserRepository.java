package com.codeclan.example.Muse.repositories;

import com.codeclan.example.Muse.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
