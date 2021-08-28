package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.services.MotivoPerdaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.repository.Filter.MotivoPerdaFilter;
import br.com.blinkdev.leadsponge.repository.motivoPerda.MotivoPerdaRepository;

@Service
public class MotivoPerdaServiceImpl extends ErroMessage implements MotivoPerdaService {

	@Autowired
	private MotivoPerdaRepository repository;

	@Override
	public MotivoPerda salvar(MotivoPerda motivoPerda) {
		return repository.save(motivoPerda);
	}

	@Override
	public MotivoPerda atualizar(Long id, MotivoPerda motivoPerda) {
		MotivoPerda novomotivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
		BeanUtils.copyProperties(motivoPerda, novomotivoPerda, "id");
		return repository.save(novomotivoPerda);
	}

	@Override
	public MotivoPerda deletar(Long id) {
		MotivoPerda motivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
		repository.deleteById(id);
		return motivoPerda;
	}

	@Override
	public MotivoPerda detalhar(Long id) {
		// TODO fazer as devidas validações
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
	}

	@Override
	public Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable) {
		return repository.filtrar(motivoPerdaFilter, pageable);
	}
}
