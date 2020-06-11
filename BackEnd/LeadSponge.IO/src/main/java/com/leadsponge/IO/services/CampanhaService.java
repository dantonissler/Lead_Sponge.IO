package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class CampanhaService {

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	public Campanha save(Campanha campanha) {
		campanhavalidar(campanha);
		return campanhaRepository.save(campanha);
	}
	
	public Campanha atualizar(Long id, Campanha campanha) {
		Campanha campanhaSalvo = buscarCampanhaExistente(id);
		BeanUtils.copyProperties(campanha, campanhaSalvo, "id");
		return campanhaRepository.save(campanhaSalvo);
	}
	
	private Campanha buscarCampanhaExistente(Long id) {
		Optional<Campanha> campanhaSalvo = campanhaRepository.findById(id);
		if (!campanhaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return campanhaSalvo.get();
	}
	
	private void campanhavalidar(Campanha campanha) {
		if (campanha == null) {
			throw new UsuarioInativaException();
		}
	}

}
