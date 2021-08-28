package br.com.blinkdev.leadsponge.repository.negociacao;

import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegociacaoRepository  extends JpaRepository<Negociacao, Long>, NegociacaoRepositoryQuery  {

}
