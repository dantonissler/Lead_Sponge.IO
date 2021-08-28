package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.MotivoPerdaFilter;

@Service
public interface MotivoPerdaService {
	MotivoPerda salvar(MotivoPerda motivoPerda);

	MotivoPerda atualizar(Long id, MotivoPerda motivoPerda);

	MotivoPerda deletar(Long id);

	MotivoPerda detalhar(Long id);

	Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
