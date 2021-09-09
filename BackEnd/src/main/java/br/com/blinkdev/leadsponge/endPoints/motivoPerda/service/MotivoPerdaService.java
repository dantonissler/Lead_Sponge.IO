package br.com.blinkdev.leadsponge.endPoints.motivoPerda.service;

import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.filter.MotivoPerdaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MotivoPerdaService {
	MotivoPerdaEntity salvar(MotivoPerdaEntity motivoPerda);

	MotivoPerdaEntity atualizar(Long id, MotivoPerdaEntity motivoPerda);

	MotivoPerdaEntity deletar(Long id);

	MotivoPerdaEntity detalhar(Long id);

	Page<MotivoPerdaEntity> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
