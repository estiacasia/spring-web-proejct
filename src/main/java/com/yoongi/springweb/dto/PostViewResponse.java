package com.yoongi.springweb.dto;

import com.yoongi.springweb.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostViewResponse {

    private Long postId;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public PostViewResponse(Post post) {
        this.postId = post.getPostId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreateAt();
    }
}
