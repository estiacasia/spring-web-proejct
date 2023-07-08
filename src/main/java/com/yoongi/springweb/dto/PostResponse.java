package com.yoongi.springweb.dto;

import com.yoongi.springweb.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {

    private final String title;
    private final String content;
    private final String userId;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
    }
}
