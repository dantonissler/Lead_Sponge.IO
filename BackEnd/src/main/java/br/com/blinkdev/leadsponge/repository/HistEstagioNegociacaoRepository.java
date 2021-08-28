package br.com.blinkdev.leadsponge.repository;

import br.com.blinkdev.leadsponge.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistEstagioNegociacaoRepository extends JpaRepository<HistEstagioNegociacao, Long>{

}
