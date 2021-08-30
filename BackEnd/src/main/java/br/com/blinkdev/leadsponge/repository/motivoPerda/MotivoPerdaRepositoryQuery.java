package br.com.blinkdev.leadsponge.repository.motivoPerda;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerdaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoPerdaRepositoryQuery {
	Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
