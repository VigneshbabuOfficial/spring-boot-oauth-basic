package com.oauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.entity.Comment;
import com.oauth.entity.Post;
import com.oauth.repository.CommentRepository;
import com.oauth.repository.PostRepository;

@RestController
@RequestMapping("post")
public class ApplicationController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post savePost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/{id}/comment")
    public List<Comment> getPostsComment(@PathVariable(value = "id")Integer postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    @PostMapping("/{id}/comment")
    public Comment saveComment(@PathVariable(value = "id")Integer postId,
                               @RequestBody Comment comment) throws Exception {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new Exception());
    }

}
