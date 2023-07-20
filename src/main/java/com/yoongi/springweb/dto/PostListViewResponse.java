package com.yoongi.springweb.dto;

import com.yoongi.springweb.domain.Post;
import lombok.Getter;

@Getter
public class PostListViewResponse {

    private final Long postId;
    private final String userId;
    private final String title;

    public PostListViewResponse(Post post) {
        this.postId = post.getPostId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
    }
}
