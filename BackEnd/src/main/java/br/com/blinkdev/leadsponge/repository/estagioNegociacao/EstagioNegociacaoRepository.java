package br.com.blinkdev.leadsponge.repository.estagioNegociacao;

import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstagioNegociacaoRepository extends JpaRepository<EstagioNegociacao, Long>, EstagioNegociacaoRepositoryQuery {

}
