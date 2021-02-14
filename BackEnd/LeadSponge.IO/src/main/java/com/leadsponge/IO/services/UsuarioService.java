package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.models.usuario.UsuarioTO;
import com.leadsponge.IO.repository.Filter.UsuarioFilter;
import com.leadsponge.IO.repository.projection.ResumoUsuario;

@Service
public interface UsuarioService {
	Usuario atualizarUsuarioDTO(Long id, UsuarioTO usuario);

	void removerImg(Long id);

	void atualizarImg(Long id, String foto);

	Usuario salvar(Usuario usuario);

	Usuario atualizar(Long id, Usuario usuario);

	void atualizarPropriedadeEnabled(Long id, Boolean enabled);

	String findLoggedInLogin();

	Usuario deletar(Long id);

	Usuario detalhar(Long id);

	Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
	
	Page<ResumoUsuario> resumir(UsuarioFilter usuarioFilter, Pageable pageable);
	
	Usuario findByNome(String username);
}
