package br.com.blinkdev.leadsponge.endPoints.contato.service;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import br.com.blinkdev.leadsponge.endPoints.contato.filter.ContatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ContatoService {
	ContatoEntity salvar(ContatoEntity contato);

	ContatoEntity atualizar(Long id, ContatoEntity contato);

	ContatoEntity deletar(Long id);

	ContatoEntity detalhar(Long id);

	Page<ContatoEntity> filtrar(ContatoFilter clienteFilter, Pageable pageable);
}
