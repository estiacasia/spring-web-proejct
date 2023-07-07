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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}