package com.leadsponge.IO.repository.campanha;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.campanha.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Long>, CampanhaRepositoryQuery {
//	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
