package com.leadsponge.IO.repository.estagioNegociacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;

public interface EstagioNegociacaoRepository extends JpaRepository<EstagioNegociacao, Long>, EstagioNegociacaoRepositoryQuery {

}
