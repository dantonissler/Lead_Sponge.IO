package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.filter.EstagioNegociacaoFilter;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.repository.EstagioNegociacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstagioNegociacaoServiceImpl extends ErroMessage implements EstagioNegociacaoService {

	@Autowired
	private EstagioNegociacaoRepository repository;

	@Override
	public EstagioNegociacaoEntity salvar(EstagioNegociacaoEntity estagioNegociacao) {
		return repository.save(estagioNegociacao);
	}

	@Override
	public EstagioNegociacaoEntity atualizar(Long id, EstagioNegociacaoEntity estagioNegociacao) {
		EstagioNegociacaoEntity estagioNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
		BeanUtils.copyProperties(estagioNegociacao, estagioNegociacaoSalvo, "id");
		return repository.save(estagioNegociacao);
	}

	@Override
	public Page<EstagioNegociacaoEntity> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
		return repository.filtrar(estagioNegociacaoFilter, pageable);
	}

	@Override
	public EstagioNegociacaoEntity deletar(Long id) {
		EstagioNegociacaoEntity estagioNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
		repository.deleteById(id);
		return estagioNegociacaoSalvo;
	}

	@Override
	public EstagioNegociacaoEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
	}
}
