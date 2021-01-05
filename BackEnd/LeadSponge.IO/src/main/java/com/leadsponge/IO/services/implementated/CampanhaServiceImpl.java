package com.leadsponge.IO.services.implementated;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.services.CampanhaService;

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
		campanhaValidar(campanha);
		return repository.save(campanha);
	}

	@Override
	public Campanha atualizar(Long id, Campanha campanha) {
		campanhaValidar(campanha);
		Campanha campanhaSalvo = buscarCampanhaExistente(id);
		BeanUtils.copyProperties(campanha, campanhaSalvo, "id");
		return repository.save(campanhaSalvo);
	}

	@Override
	public Boolean deletar(Long id) {
		try {
			repository.deleteById(id);
			Optional<Campanha> campanhaDeletado = repository.findById(id);
			if (campanhaDeletado.isPresent())
				return true;
			else
				return false;
		} catch (Exception e) {
			throw notFouldId(id, "a campanha");
		}
	}

	@Override
	public Campanha detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}

	private Campanha buscarCampanhaExistente(Long id) {
		Optional<Campanha> campanhaSalvo = repository.findById(id);
		if (!campanhaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return campanhaSalvo.get();
	}

	private void campanhaValidar(Campanha campanha) {
		if (campanha == null) {
			throw new UsuarioInativaException();
		}
	}

}
