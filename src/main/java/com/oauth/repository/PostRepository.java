package com.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
