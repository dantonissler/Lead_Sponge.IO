package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.UsuarioFilter;
import br.com.blinkdev.leadsponge.repository.projection.UsuarioResumo;

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
	
	Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable);
	
	Usuario findByNome(String username);
}
