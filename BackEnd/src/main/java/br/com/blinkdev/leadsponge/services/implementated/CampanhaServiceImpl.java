package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.models.campanha.Campanha;
import br.com.blinkdev.leadsponge.services.CampanhaService;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.repository.Filter.CampanhaFilter;
import br.com.blinkdev.leadsponge.repository.campanha.CampanhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j // TODO: criar um log para os metodos
@Service
public class CampanhaServiceImpl extends ErroMessage implements CampanhaService {

	@Autowired
	private CampanhaRepository repository;

	@Override
	public Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable) {
		return repository.filtrar(campanhaFilter, pageable);
	}

	@Override
	public Campanha salvar(Campanha campanha) {
		return repository.save(campanha);
	}

	@Override
	public Campanha atualizar(Long id, Campanha campanha) {
		Campanha campanhaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		BeanUtils.copyProperties(campanha, campanhaSalvo, "id");
		return repository.save(campanhaSalvo);
	}

	@Override
	public Campanha deletar(Long id) {
		Campanha campanhaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		repository.deleteById(id);
		return campanhaSalvo;
	}

	@Override
	public Campanha detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}
}
