package br.com.blinkdev.leadsponge.endPoints.segmento.service;

import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.filter.SegmentoFilter;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.segmento.repository.SegmentoRepository;
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
	public SegmentoEntity salvar(SegmentoEntity segmento) {
		return repository.save(segmento);
	}

	@Override
	public SegmentoEntity atualizar(Long id, SegmentoEntity segmento) {
		SegmentoEntity fonteSegmento = repository.findById(id).orElseThrow(() -> notFouldId(id, "a segmento"));
		BeanUtils.copyProperties(segmento, fonteSegmento, "id");
		return repository.save(fonteSegmento);
	}

	@Override
	public Page<SegmentoEntity> filtrar(SegmentoFilter segmentoFilter, Pageable pageable) {
		return repository.filtrar(segmentoFilter, pageable);
	}

	@Override
	public SegmentoEntity deletar(Long id) {
		SegmentoEntity segmentoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		repository.deleteById(id);
		return segmentoSalvo;
	}

	@Override
	public SegmentoEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}
	
	

}
