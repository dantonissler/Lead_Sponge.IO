package br.com.blinkdev.leadsponge.endpoints.token;

import br.com.blinkdev.leadsponge.config.property.LeadSpongeApiProperty;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "tokens", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Tokens")
class TokenController {

    @Autowired
    private final LeadSpongeApiProperty leadSpongeApiProperty;

    public TokenController(LeadSpongeApiProperty leadSpongeApiProperty) {
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
