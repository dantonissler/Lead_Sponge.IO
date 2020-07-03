package com.leadsponge.IO.repository.negociacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.negociacao.Negociacao;

public interface NegociacaoRepository  extends JpaRepository<Negociacao, Long>, NegociacaoRepositoryQuery  {

}
