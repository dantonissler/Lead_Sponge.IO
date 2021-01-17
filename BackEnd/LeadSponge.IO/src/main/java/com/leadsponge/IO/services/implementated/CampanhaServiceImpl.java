package com.leadsponge.IO.services.implementated;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.services.CampanhaService;

@Service
public class CampanhaServiceImpl extends ErroMessage implements CampanhaService {

	@Autowired
	private CampanhaRepository repository;

	@Override
	@Transactional
	public Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable) {
		return repository.filtrar(campanhaFilter, pageable);
	}

	@Override
	@Transactional
	public Campanha salvar(Campanha campanha) {
		if (repository.existsById(campanha.getId())) {
			throw notFouldId(campanha.getId(), "a campanha");
		}
		return repository.save(campanha);
	}

	@Override
	@Transactional
	public Campanha atualizar(Long id, Campanha campanha) {
		Campanha campanhaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		BeanUtils.copyProperties(campanha, campanhaSalvo, "id");
		return repository.save(campanhaSalvo);
	}

	@Override
	@Transactional
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
