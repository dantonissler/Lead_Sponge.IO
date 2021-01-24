package com.leadsponge.IO.repository.telefone;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.telefone.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>, TelefoneRepositoryQuery {
	Iterable<Telefone> findByContatoTelefone(Contato contato);
}
