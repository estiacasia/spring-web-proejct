package com.yoongi.springweb.controller;

import com.yoongi.springweb.domain.Post;
import com.yoongi.springweb.dto.AddPostRequest;
import com.yoongi.springweb.dto.PostResponse;
import com.yoongi.springweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequest request) {
        Post savedPost = postService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        List<PostResponse> posts = postService.findAll()
                .stream()
                .map(PostResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(posts);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable Long postId) {
        Post post = postService.findByPostId(postId);

        return ResponseEntity.ok()
                .body(new PostResponse(post));
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.delete(postId);

        return ResponseEntity.ok()
                .build();
    }
}
