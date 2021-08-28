package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.services.EstagioNegociacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.repository.Filter.EstagioNegociacaoFilter;
import br.com.blinkdev.leadsponge.repository.estagioNegociacao.EstagioNegociacaoRepository;

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
