package br.com.blinkdev.leadsponge.services.segmento;

import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.segmento.SegmentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SegmentoService {
	Segmento salvar(Segmento segmento);

	Segmento atualizar(Long id, Segmento segmento);

	Segmento deletar(Long id);

	Segmento detalhar(Long id);

	Page<Segmento> filtrar(SegmentoFilter negociacaoFilter, Pageable pageable);
}
