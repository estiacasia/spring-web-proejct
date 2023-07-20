package com.yoongi.springweb.dto;

import com.yoongi.springweb.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostViewResponse {

    private final Long postId;
    private final String userId;
    private final String title;
    private final String content;
    private LocalDateTime createdAt;

    public PostViewResponse(Post post) {
        this.postId = post.getPostId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreateAt();
    }
}
