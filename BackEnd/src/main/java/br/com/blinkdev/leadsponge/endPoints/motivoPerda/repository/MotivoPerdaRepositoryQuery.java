package br.com.blinkdev.leadsponge.endPoints.motivoPerda.repository;

import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.filter.MotivoPerdaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoPerdaRepositoryQuery {
	Page<MotivoPerdaEntity> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
