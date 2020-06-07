package com.leadsponge.IO.endPoints;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.config.property.LeadSpongeApiProperty;

@Profile("oauth-security")
@RestController
@RequestMapping("/tokens")
class TokenEndPoint {

	@Autowired
	private final LeadSpongeApiProperty leadSpongeApiProperty;
	
	public TokenEndPoint(LeadSpongeApiProperty leadSpongeApiProperty) {
		this.leadSpongeApiProperty = leadSpongeApiProperty;
	}

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(leadSpongeApiProperty.getSeguranca().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
