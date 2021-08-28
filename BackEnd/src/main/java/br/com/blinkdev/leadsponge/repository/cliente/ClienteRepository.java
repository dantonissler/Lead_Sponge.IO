package br.com.blinkdev.leadsponge.repository.cliente;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery{
}
