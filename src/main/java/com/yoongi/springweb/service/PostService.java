package com.yoongi.springweb.service;

import com.yoongi.springweb.domain.Post;
import com.yoongi.springweb.dto.AddPostRequest;
import com.yoongi.springweb.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Post save(AddPostRequest request) {
        return postRepository.save(request.toEntity());
    }
}
