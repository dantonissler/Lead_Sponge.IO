package br.com.blinkdev.leadsponge.endPoints.historicoEstagioNegociacao;

import br.com.blinkdev.leadsponge.endPoints.historicoEstagioNegociacao.entity.HistEstagioNegociacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistEstagioNegociacaoRepository extends JpaRepository<HistEstagioNegociacao, Long>{

}
