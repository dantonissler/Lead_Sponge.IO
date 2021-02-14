package com.leadsponge.leadsponge.IO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.JSONObject;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

public class Util {
    public static String getAccessToken(final String username, final String password, final MockMvc mockMvc) throws Exception {
        try {
            MockHttpServletResponse response = mockMvc.perform(post("/oauth/token").header("Authorization", "Basic QExlYWRfU3BvbmdlMTIz").param("username", username).param("password", password).param("grant_type", "password"))
                    .andReturn().getResponse();
            JSONObject obj = new JSONObject(response.getContentAsString());
            if (obj.has("access_token"))
                return obj.getString("access_token");
            else
                return "";
        } catch (Exception e) {
            throw new Exception();
        }
    }
    // Converter uma string para base64: new String(Base64Utils.encode(("@Lead_Sponge123").getBytes()))
}
