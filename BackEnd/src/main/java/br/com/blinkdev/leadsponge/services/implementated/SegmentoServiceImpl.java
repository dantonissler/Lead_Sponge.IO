package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.segmento.SegmentoFilter;
import br.com.blinkdev.leadsponge.repository.segmento.SegmentoRepository;
import br.com.blinkdev.leadsponge.services.SegmentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SegmentoServiceImpl extends ErroMessage implements SegmentoService {
	@Autowired
	private SegmentoRepository repository;

	@Override
	public Segmento salvar(Segmento segmento) {
		return repository.save(segmento);
	}

	@Override
	public Segmento atualizar(Long id, Segmento segmento) {
		Segmento fonteSegmento = repository.findById(id).orElseThrow(() -> notFouldId(id, "a segmento"));
		BeanUtils.copyProperties(segmento, fonteSegmento, "id");
		return repository.save(fonteSegmento);
	}

	@Override
	public Page<Segmento> filtrar(SegmentoFilter segmentoFilter, Pageable pageable) {
		return repository.filtrar(segmentoFilter, pageable);
	}

	@Override
	public Segmento deletar(Long id) {
		Segmento segmentoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		repository.deleteById(id);
		return segmentoSalvo;
	}

	@Override
	public Segmento detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}
	
	

}
