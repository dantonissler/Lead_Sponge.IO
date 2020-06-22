package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.contato.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;
	
	public Contato salvar(Contato contato) {
		contato.getTelefone().forEach(c -> c.setContato(contato));
		contato.getEmail().forEach(c -> c.setContato(contato));
		return contatoRepository.save(contato);
	}
	
	public Contato atualizar(Long id, Contato contato) {
		Contato contatoSalva = buscarContatoExistente(id);
		
		contatoSalva.getTelefone().clear();
		contatoSalva.getEmail().clear();
		
//		contatoSalva.getTelefone().addAll(contato.getTelefone());
		
		contatoSalva.getTelefone().forEach(c -> c.setContato(contatoSalva));
		contatoSalva.getEmail().forEach(c -> c.setContato(contatoSalva));
		
		return contatoRepository.save(contatoSalva);
	}
	
	private Contato buscarContatoExistente(Long id) {
		Optional<Contato> campanhaSalvo = contatoRepository.findById(id);
		if (!campanhaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return campanhaSalvo.get();
	}
}
