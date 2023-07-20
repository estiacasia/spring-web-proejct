package com.yoongi.springweb.controller;

import com.yoongi.springweb.domain.Post;
import com.yoongi.springweb.dto.PostListViewResponse;
import com.yoongi.springweb.dto.PostViewResponse;
import com.yoongi.springweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostViewController {
    private final PostService postService;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        // List<Post> -> Stream<Post> -> Stream<PostListViewResponse> -> List<PostListViewResponse>
        List<PostListViewResponse> posts = postService.findAll().stream()
                .map(PostListViewResponse::new)
                .toList();
        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        Post post = postService.findByPostId(postId);
        model.addAttribute("post", new PostViewResponse(post));

        return "post";
    }
}
