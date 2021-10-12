package br.com.blinkdev.leadsponge.endpoints.user.service;

import br.com.blinkdev.leadsponge.endpoints.user.TO.UsuarioSistemaTO;
import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endpoints.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> usuarioOptional = usuarioRepository.findByUsername(username);
        UserEntity usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
        if (!usuario.isEnabled()) {
            throw new UserDeniedAuthorizationException("Usuário inativo");
		}
		return new UsuarioSistemaTO(usuario, getRoles(usuario));
		
	}
	
	private Collection<? extends GrantedAuthority> getRoles(UserEntity usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getAuthority().toUpperCase())));
		return authorities;
	}

}
