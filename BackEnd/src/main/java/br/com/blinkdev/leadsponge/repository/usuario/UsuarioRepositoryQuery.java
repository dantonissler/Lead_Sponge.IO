package br.com.blinkdev.leadsponge.repository.usuario;

import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioFilter;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoryQuery {

	Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

	Page<UsuarioModel> resumir(UsuarioFilter usuarioFilter, Pageable pageable);
}
