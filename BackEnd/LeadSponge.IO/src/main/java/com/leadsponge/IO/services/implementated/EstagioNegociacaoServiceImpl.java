package com.leadsponge.IO.services.implementated;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;
import com.leadsponge.IO.repository.estagioNegociacao.EstagioNegociacaoRepository;
import com.leadsponge.IO.services.EstagioNegociacaoService;

@Service
public class EstagioNegociacaoServiceImpl extends ErroMessage implements EstagioNegociacaoService {

	@Autowired
	private EstagioNegociacaoRepository repository;

	@Override
	public EstagioNegociacao salvar(EstagioNegociacao estagioNegociacao) {
		return repository.save(estagioNegociacao);
	}

	@Override
	public EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao) {
		EstagioNegociacao estagioNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
		BeanUtils.copyProperties(estagioNegociacao, estagioNegociacaoSalvo, "id");
		return repository.save(estagioNegociacao);
	}

	@Override
	public Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
		return repository.filtrar(estagioNegociacaoFilter, pageable);
	}

	@Override
	public EstagioNegociacao deletar(Long id) {
		EstagioNegociacao estagioNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
		repository.deleteById(id);
		return estagioNegociacaoSalvo;
	}

	@Override
	public EstagioNegociacao detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
	}
}
