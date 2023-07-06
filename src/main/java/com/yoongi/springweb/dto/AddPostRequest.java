package com.yoongi.springweb.dto;

import com.yoongi.springweb.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddPostRequest {

    private String title;
    private String content;
    private String userId;

    // Converts DTO to Post entity
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
