package br.com.blinkdev.leadsponge.endPoints.email.repository;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long>, EmailRepositoryQuery {
	Iterable<EmailEntity> findByContatoEmail(ContatoEntity contato);
}
