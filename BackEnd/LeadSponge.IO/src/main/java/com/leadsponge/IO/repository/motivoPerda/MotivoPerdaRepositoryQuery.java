package com.leadsponge.IO.repository.motivoPerda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.Filter.MotivoPerdaFilter;

@Repository
public interface MotivoPerdaRepositoryQuery {
	public Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
