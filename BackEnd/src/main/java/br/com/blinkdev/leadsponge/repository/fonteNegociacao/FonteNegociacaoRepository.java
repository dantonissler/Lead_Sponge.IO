package br.com.blinkdev.leadsponge.repository.fonteNegociacao;

import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FonteNegociacaoRepository extends JpaRepository<FonteNegociacao, Long>, FonteNegociacaoRepositoryQuery {

}
