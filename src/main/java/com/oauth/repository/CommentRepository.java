package com.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByPostId(Integer postId);

}
