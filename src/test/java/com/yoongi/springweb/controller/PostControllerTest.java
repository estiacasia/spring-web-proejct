package com.yoongi.springweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoongi.springweb.domain.Post;
import com.yoongi.springweb.dto.AddPostRequest;
import com.yoongi.springweb.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    // serialization and deserialization of Java objects and JSON
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        postRepository.deleteAll();
    }

    @DisplayName("Testing for adding post")
    @Test
    public void addPost() throws Exception {
        // given
        final String url = "/posts";
        final String title = "testTitle";
        final String content = "testContent";
        final String user_id = "test_id";
        final AddPostRequest addPostRequest = new AddPostRequest(title, content, user_id);

        // serialization
        final String requestBody = objectMapper.writeValueAsString(addPostRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
        assertThat(posts.get(0).getUserId()).isEqualTo(user_id);
    }

    @DisplayName("Testing for getting posts")
    @Test
    public void getPosts() throws Exception {

        //given
        final String url = "/posts";

        final String title1 = "title1";
        final String content1 = "content1";
        final String userId1 = "userId1";
        final String title2 = "title2";
        final String content2 = "content2";
        final String userId2 = "userId2";

        postRepository.save(Post.builder()
                .title(title1)
                .content(content1)
                .userId(userId1)
                .build());

        postRepository.save(Post.builder()
                .title(title2)
                .content(content2)
                .userId(userId2)
                .build());

        //when
        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title1))
                .andExpect(jsonPath("$[0].content").value(content1))
                .andExpect(jsonPath("$[0].userId").value(userId1))
                .andExpect(jsonPath("$[1].title").value(title2))
                .andExpect(jsonPath("$[1].content").value(content2))
                .andExpect(jsonPath("$[1].userId").value(userId2));
    }

    @DisplayName("Testing for getting a certain post")
    @Test
    public void findPost() throws Exception {
        // given
        final String url = "/posts/{id}";
        final String title = "title";
        final String content = "content";
        final String userId = "userId";

        Post savedPost = postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform((get(url,savedPost.getPostId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @DisplayName("Testing for deleting a certain post")
    @Test
    public void deletePost() throws Exception {
        //given
        final String url = "/posts/{id}";
        final String title = "title";
        final String content = "content";
        final String userId = "userId";

        Post savedPost = postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build());

        //when
        mockMvc.perform(delete(url, savedPost.getPostId()))
                .andExpect(status().isOk());

        //then
        List<Post> posts = postRepository.findAll();

        assertThat(posts).isEmpty();
    }
}