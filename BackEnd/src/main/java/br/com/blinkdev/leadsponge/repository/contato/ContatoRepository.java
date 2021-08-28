package br.com.blinkdev.leadsponge.repository.contato;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>, ContatoRepositoryQuery {
	Iterable<Contato> findByClienteContato(Cliente cliente);
}
