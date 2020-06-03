package com.leadsponge.IO.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.Usuario;
import com.leadsponge.IO.repository.usuario.UsuarioRepositoryQuery;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

	public Optional<Usuario> findByUsername(String username);

	public List<Usuario> findByRoles(String nomeRole);
}
