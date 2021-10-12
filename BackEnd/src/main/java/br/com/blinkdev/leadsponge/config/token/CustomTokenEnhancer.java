package br.com.blinkdev.leadsponge.config.token;

import java.util.HashMap;
import java.util.Map;

import br.com.blinkdev.leadsponge.endpoints.user.TO.UsuarioSistemaTO;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistemaTO usuarioSistema = (UsuarioSistemaTO) authentication.getPrincipal();
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuarioSistema.getUsuario().getNomeCompleto());
		addInfo.put("foto", usuarioSistema.getUsuario().getFoto());
		addInfo.put("email", usuarioSistema.getUsuario().getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}
}
