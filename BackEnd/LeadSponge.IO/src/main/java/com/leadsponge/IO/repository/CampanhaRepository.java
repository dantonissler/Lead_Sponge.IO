package com.leadsponge.IO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.models.negociacao.Negociacao;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
