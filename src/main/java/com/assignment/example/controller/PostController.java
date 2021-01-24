package com.assignment.example.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.example.Util.PostUtil;
import com.assignment.example.exception.ResourceNotFoundException;
import com.assignment.example.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author manoj
 *
 */
@RestController
@RequestMapping("/api/v1")
public class PostController {
	
	
	private Post post;
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	/**
	 *
	 *
	 * @param
	 * @return total post count
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws URISyntaxException
	 */
	@GetMapping("/posts/")
	public ResponseEntity<Map<String, Object>> getPostCount() throws ResourceNotFoundException, URISyntaxException {
		
		PostUtil.checkData();
		
		Map<String, Object> data = new HashMap<>();
		
		long totalpost = PostUtil.getPostLst().stream().count();

		data.put("totalpost", totalpost);

		return ResponseEntity.ok().body(PostUtil.generateSuccesResp(data, ""));
	}

	/**
	 *
	 *
	 * @return unique Users Count
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws URISyntaxException
	 */
	@GetMapping("/posts/users/")
	public ResponseEntity<Map<String, Object>> getUniquesUsersCount() throws ResourceNotFoundException, URISyntaxException {

		PostUtil.checkData();
		
		Map<String, Object> data = new HashMap<>();

		long usersCount = PostUtil.getPostLst().stream().map(Post::getUserId).distinct().count();

		data.put("totalUsersCount", usersCount);

		return ResponseEntity.ok().body(PostUtil.generateSuccesResp(data, ""));
	}

	/**
	 * Update 4th post response entity.
	 *
	 * @return the response entity to java class
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws URISyntaxExceptionpostList
	 */
	@PutMapping("/post/")
	public ResponseEntity<Post> updatePost() throws ResourceNotFoundException, URISyntaxException {
		
		PostUtil.checkData();
		
		List<Post> postList =  PostUtil.getPostLst();

		postList.forEach(e -> {
			if(e.getId() == 5) {
				e.setBody("1800Flowers");
				e.setTitle("1800Flowers");
				setPost(e);
			}
		});
		
		PostUtil.setPostLst(postList);
		
		return ResponseEntity.ok(getPost());
	}

	/**
	 * @return the response entity to Json and 4th to java class
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws URISyntaxException
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/posts")
	public ResponseEntity<Map<String, Object>> getUpdatedPosts() throws ResourceNotFoundException, URISyntaxException, JsonProcessingException {
		
		PostUtil.checkData();
		
		Map<String, Object> data = new HashMap<>();
		
		List<Post> postList =  PostUtil.getPostLst();
		
		Optional<Post> postObj = postList.stream().filter(p -> p.getId() == 5).findFirst();
		
		data.put("post", postObj.orElse(null));
		data.put("jsonPostLst", PostUtil.convertListToJson(postList));
		
		return ResponseEntity.ok(PostUtil.generateSuccesResp(data, ""));
	}
	
	/**
	 *
	 *
	 * @return updated User List
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws URISyntaxException
	 */
	@GetMapping("/posts/users")
	public ResponseEntity<Map<String, Object>> getUpdatedUserList() throws ResourceNotFoundException, URISyntaxException {

		PostUtil.checkData();
		
		Map<String, Object> data = new HashMap<>();

		List<Long> userIds = PostUtil.getPostLst().stream().map(Post::getUserId).distinct().collect(Collectors.toList());

		data.put("userList", userIds);

		return ResponseEntity.ok().body(PostUtil.generateSuccesResp(data, ""));
	}
}
