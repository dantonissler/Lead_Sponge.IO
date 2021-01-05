package com.leadsponge.IO.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.leadsponge.IO.models.usuario.UsuarioSistemaTO;

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
