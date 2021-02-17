package com.leadsponge.IO.repository.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.Filter.UsuarioFilter;
import com.leadsponge.IO.repository.projection.UsuarioResumo;

@Repository
public interface UsuarioRepositoryQuery {

	Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

	Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable);
}
