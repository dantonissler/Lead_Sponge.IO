package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.Filter.MotivoPerdaFilter;

@Service
public interface MotivoPerdaService {
	MotivoPerda salvar(MotivoPerda motivoPerda);

	MotivoPerda atualizar(Long id, MotivoPerda motivoPerda);

	MotivoPerda deletar(Long id);

	MotivoPerda detalhar(Long id);

	Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
