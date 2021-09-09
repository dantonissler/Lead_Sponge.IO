package br.com.blinkdev.leadsponge.endPoints.motivoPerda.service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.filter.MotivoPerdaFilter;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.repository.MotivoPerdaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MotivoPerdaServiceImpl extends ErroMessage implements MotivoPerdaService {

	@Autowired
	private MotivoPerdaRepository repository;

	@Override
	public MotivoPerdaEntity salvar(MotivoPerdaEntity motivoPerda) {
		return repository.save(motivoPerda);
	}

	@Override
	public MotivoPerdaEntity atualizar(Long id, MotivoPerdaEntity motivoPerda) {
		MotivoPerdaEntity novomotivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
		BeanUtils.copyProperties(motivoPerda, novomotivoPerda, "id");
		return repository.save(novomotivoPerda);
	}

	@Override
	public MotivoPerdaEntity deletar(Long id) {
		MotivoPerdaEntity motivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
		repository.deleteById(id);
		return motivoPerda;
	}

	@Override
	public MotivoPerdaEntity detalhar(Long id) {
		// TODO fazer as devidas validações
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
	}

	@Override
	public Page<MotivoPerdaEntity> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable) {
		return repository.filtrar(motivoPerdaFilter, pageable);
	}
}
