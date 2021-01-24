package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.Filter.MotivoPerdaFilter;

@Service
public interface MotivoPerdaService {
	public MotivoPerda salvar(MotivoPerda motivoPerda);

	public MotivoPerda atualizar(Long id, MotivoPerda motivoPerda);

	public MotivoPerda deletar(Long id);

	public MotivoPerda detalhar(Long id);

	public Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
