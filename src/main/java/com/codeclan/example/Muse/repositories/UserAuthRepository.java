package com.codeclan.example.Muse.repositories;

import com.codeclan.example.Muse.models.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
}
