package com.leadsponge.IO.repository.fonteNegociacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;

public interface FonteNegociacaoRepository extends JpaRepository<FonteNegociacao, Long>, FonteNegociacaoRepositoryQuery {

}
