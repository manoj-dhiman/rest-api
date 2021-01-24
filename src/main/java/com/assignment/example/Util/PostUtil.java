package com.assignment.example.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.assignment.example.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author manoj
 *
 */
public final class PostUtil {
	
	private static final String URL = "http://jsonplaceholder.typicode.com/posts";
	
	private static List<Post> postLst = new ArrayList<>();

	/**
	 * 
	 * @param <T>
	 * @param data
	 * @param message
	 * @return
	 */
	public static <T> Map<String, Object> generateSuccesResp(T data, String message) {
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("message", message);
		resp.put("data", data);
		return resp;
	}
	
	/**
	 * 
	 * @param post list
	 * @return list to Json 
	 * @throws JsonProcessingException 
	 */
	public static String convertListToJson(List<Post> poList) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(poList);
	}
	
	public static void checkData() {
		if(CollectionUtils.isEmpty(getPostLst())) {
			Map<String, Object> data = new HashMap<>();
			generateErrorResp(data, "Data Not FOund");
		}
	}
	
	public static <T> Map<String,Object> generateErrorResp(T data,String message) {
		Map<String,Object> resp =new HashMap<String, Object>();
		resp.put("message", message);
		resp.put("status", 0);
		return resp;
	}

	public static String getUrl() {
		return URL;
	}

	public static List<Post> getPostLst() {
		return postLst;
	}

	public static void setPostLst(List<Post> postLst) {
		PostUtil.postLst = postLst;
	}
}
