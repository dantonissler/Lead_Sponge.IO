package com.leadsponge.IO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.Campanha;
import com.leadsponge.IO.models.Oportunidade;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
	Iterable<Oportunidade> findByOportunidades(Oportunidade contato);
}
