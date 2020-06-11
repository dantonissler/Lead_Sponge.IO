package com.leadsponge.IO.repository.segmento;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.segmento.Segmento;

public interface SegmentoRepository extends JpaRepository<Segmento, Long>, SegmentoRepositoryQuery {

}
