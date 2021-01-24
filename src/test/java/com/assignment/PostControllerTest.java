package com.assignment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assignment.example.controller.PostController;
import com.assignment.example.service.PostServiceImpl;

@SpringBootTest(classes = {PostController.class, PostServiceImpl.class})
@AutoConfigureMockMvc
public class PostControllerTest {
	
    private MockMvc mockMvc;
    
    public PostControllerTest() throws URISyntaxException, IOException {
    	mockMvc = MockMvcBuilders.standaloneSetup(new PostController()).build();
    	new PostServiceImpl();
    }
    
    @Test
    public void getPostCount() throws Exception {
    	 mockMvc.perform(get("/api/v1/posts/")).andExpect(status().isOk()).andDo(print());
    }
    @Test
	public void getUniquesUsersCount() throws Exception {
		mockMvc.perform(get("/api/v1/posts/users/")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void updatePost() throws Exception {
		mockMvc.perform(put("/api/v1/post/")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getUpdatedPosts() throws Exception {
		mockMvc.perform(get("/api/v1/posts")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void  getUpdatedUserList()  throws Exception {
		mockMvc.perform(get("/api/v1/posts/users")).andExpect(status().isOk()).andDo(print());
	}
    
}
