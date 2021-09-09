package br.com.blinkdev.leadsponge.endPoints.segmento.repository;

import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepository extends JpaRepository<SegmentoEntity, Long>, SegmentoRepositoryQuery {

}
