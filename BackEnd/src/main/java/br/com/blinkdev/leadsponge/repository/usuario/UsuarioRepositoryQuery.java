package br.com.blinkdev.leadsponge.repository.usuario;

import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.repository.Filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.blinkdev.leadsponge.repository.projection.UsuarioResumo;

@Repository
public interface UsuarioRepositoryQuery {

	Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

	Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable);
}
