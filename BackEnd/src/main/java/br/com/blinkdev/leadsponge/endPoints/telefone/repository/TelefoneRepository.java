package br.com.blinkdev.leadsponge.endPoints.telefone.repository;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>, TelefoneRepositoryQuery {
	Iterable<Telefone> findByContatoTelefone(ContatoEntity contato);
}
