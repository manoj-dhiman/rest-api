package com.assignment.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.assignment.example.Util.PostUtil;
import com.assignment.example.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author manoj
 *
 */
@Service("service")
public class PostServiceImpl {
	
	public PostServiceImpl() throws URISyntaxException, IOException {
		getPostsFromUrl();
	}
	
	/**
	 * read json from Url
	 * 
	 * @return list post
	 * @throws URISyntaxException
	 * @throws IOException 
	 */
	public void getPostsFromUrl() throws URISyntaxException, IOException {
		 try {
			URI postUrl = new URI(PostUtil.getUrl());
			HttpURLConnection con = (HttpURLConnection) postUrl.toURL().openConnection();
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("GET");
			if (con.getResponseCode() == 200) {
				BufferedReader  reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer buffer = new StringBuffer();
				for (String line = reader.readLine(); line != null; line = reader.readLine()) {
					buffer.append(line);
				}
				
				ObjectMapper mapper = new ObjectMapper();
				try {
					List<Post> list = Arrays.asList(mapper.readValue(buffer.toString(), Post[].class));
					if(!CollectionUtils.isEmpty(list))
						PostUtil.setPostLst(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
	}
}
