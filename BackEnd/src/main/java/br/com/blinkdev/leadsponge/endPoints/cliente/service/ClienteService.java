package br.com.blinkdev.leadsponge.endPoints.cliente.service;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.cliente.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {
	ClienteEntity salvar(ClienteEntity cliente);

	ClienteEntity atualizar(Long id, ClienteEntity cliente);

	ClienteEntity deletar(Long id);

	ClienteEntity detalhar(Long id);

	Page<ClienteEntity> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
