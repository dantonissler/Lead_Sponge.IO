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
	public Usuario atualizarUsuarioDTO(Long id, UsuarioTO usuario);

	public void removerImg(Long id);

	public void atualizarImg(Long id, String foto);

	public Usuario salvar(Usuario usuario);

	public Usuario atualizar(Long id, Usuario usuario);

	public void atualizarPropriedadeEnabled(Long id, Boolean enabled);

	public String findLoggedInLogin();

	public Usuario deletar(Long id);

	public Usuario detalhar(Long id);

	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
	
	public Page<ResumoUsuario> resumir(UsuarioFilter usuarioFilter, Pageable pageable);
	
	public Usuario findByNome(String username);
}
