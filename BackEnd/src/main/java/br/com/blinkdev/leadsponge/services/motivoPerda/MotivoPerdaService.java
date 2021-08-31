package br.com.blinkdev.leadsponge.services.motivoPerda;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerdaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MotivoPerdaService {
	MotivoPerda salvar(MotivoPerda motivoPerda);

	MotivoPerda atualizar(Long id, MotivoPerda motivoPerda);

	MotivoPerda deletar(Long id);

	MotivoPerda detalhar(Long id);

	Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable);
}
