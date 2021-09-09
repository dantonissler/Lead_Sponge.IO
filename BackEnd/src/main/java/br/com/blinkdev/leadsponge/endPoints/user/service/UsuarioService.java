package br.com.blinkdev.leadsponge.endPoints.user.service;

import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
import br.com.blinkdev.leadsponge.endPoints.user.TO.UsuarioTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
	UserEntity atualizarUsuarioDTO(Long id, UsuarioTO usuario);

	void removerImg(Long id);

	void atualizarImg(Long id, String foto);

	UserEntity salvar(UserEntity usuario);

	UserEntity atualizar(Long id, UserEntity usuario);

	void atualizarPropriedadeEnabled(Long id, Boolean enabled);

	String findLoggedInLogin();

	UserEntity deletar(Long id);

	UserModel detalhar(Long id);

	Page<UserEntity> filtrar(UserFilter usuarioFilter, Pageable pageable);

	Page<UserModel> list(UserFilter usuarioFilter, Pageable pageable);
	
	UserEntity findByNome(String username);
}
