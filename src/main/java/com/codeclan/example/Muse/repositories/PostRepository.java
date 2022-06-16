package com.codeclan.example.Muse.repositories;

import com.codeclan.example.Muse.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
