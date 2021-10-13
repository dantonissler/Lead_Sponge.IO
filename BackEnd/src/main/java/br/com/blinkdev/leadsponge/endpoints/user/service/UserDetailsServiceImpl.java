package br.com.blinkdev.leadsponge.endpoints.user.service;

import br.com.blinkdev.leadsponge.end_points.user.TO.UsuarioSistemaTO;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.end_points.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> usuarioOptional = userRepository.findByUsername(username);
        UserEntity userEntity = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
        if (!userEntity.isEnabled()) {
            throw new UserDeniedAuthorizationException("Usuário inativo");
		}
		return new UsuarioSistemaTO(userEntity, getRoles(userEntity));
		
	}
	
	private Collection<? extends GrantedAuthority> getRoles(UserEntity userEntity) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userEntity.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getAuthority().toUpperCase())));
		return authorities;
	}

}
