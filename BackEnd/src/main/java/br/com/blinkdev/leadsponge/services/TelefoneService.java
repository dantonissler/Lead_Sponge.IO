package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.telefone.Telefone;
import br.com.blinkdev.leadsponge.models.telefone.TelefoneFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TelefoneService {
	Telefone salvar(Telefone contato);

	Telefone atualizar(Long id, Telefone contato);

	Telefone deletar(Long id);

	Telefone detalhar(Long id);

	Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable);

}
