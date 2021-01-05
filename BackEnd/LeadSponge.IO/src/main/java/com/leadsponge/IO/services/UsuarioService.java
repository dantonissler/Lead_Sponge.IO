package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.models.usuario.UsuarioTO;

@Service
public interface UsuarioService {
	public Usuario atualizarUsuarioDTO(Long id, UsuarioTO usuario);

	public void removerImg(Long id);

	public void atualizarImg(Long id, String foto);

	public Usuario salvar(Usuario usuario);

	public Usuario atualizar(Long id, Usuario usuario);

	public void atualizarPropriedadeEnabled(Long id, Boolean enabled);

	public String findLoggedInLogin();
}
