package br.com.blinkdev.leadsponge.repository.telefone;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.models.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>, TelefoneRepositoryQuery {
	Iterable<Telefone> findByContatoTelefone(Contato contato);
}
