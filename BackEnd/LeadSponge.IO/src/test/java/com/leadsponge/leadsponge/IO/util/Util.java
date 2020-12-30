package com.leadsponge.leadsponge.IO.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.JSONObject;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

public class Util {
	public static String getAccessToken(final String username, final String password, final MockMvc mockMvc) throws Exception {
		try {
			MockHttpServletResponse response = mockMvc.perform(post("/oauth/token").header("Authorization", "Basic ZGFudG9uOjIxNDI1NWtqdWtpaQ==").param("username", username).param("password", password).param("grant_type", "password"))
					.andReturn().getResponse();
			JSONObject obj = new JSONObject(response.getContentAsString());
			if (obj.has("access_token"))
				return obj.getString("access_token");
			else
				return "";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception();
		}
	}
}
