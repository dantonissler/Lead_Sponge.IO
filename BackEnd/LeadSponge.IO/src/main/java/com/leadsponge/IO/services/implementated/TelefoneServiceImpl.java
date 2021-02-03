package com.leadsponge.IO.services.implementated;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.telefone.Telefone;
import com.leadsponge.IO.repository.Filter.TelefoneFilter;
import com.leadsponge.IO.repository.telefone.TelefoneRepository;
import com.leadsponge.IO.services.TelefoneService;

@Service
public class TelefoneServiceImpl extends ErroMessage implements TelefoneService {

	@Autowired
	private TelefoneRepository repository;

	@Override
	public Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable) {
		return repository.filtrar(telefoneFilter, pageable);
	}

	@Override
	public Telefone salvar(Telefone telefone) {
		return repository.save(telefone);
	}

	@Override
	public Telefone atualizar(Long id, Telefone telefone) {
		Telefone telefoneSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a telefone"));
		BeanUtils.copyProperties(telefone, telefoneSalvo, "id");
		return repository.save(telefoneSalvo);
	}

	@Override
	public Telefone deletar(Long id) {
		Telefone telefoneSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a telefone"));
		repository.deleteById(id);
		return telefoneSalvo;
	}

	@Override
	public Telefone detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a telefone"));
	}
}
