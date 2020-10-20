package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.estagioNegociacao.EstagioNegociacaoRepository;

@Service
public class EstagioNegociacaoService {

	@Autowired
	private EstagioNegociacaoRepository estagioNegociacaoRepository;
	
	public EstagioNegociacao save(EstagioNegociacao estagioNegociacao) {
		estagioNegociacaoValidar(estagioNegociacao);
		return estagioNegociacaoRepository.save(estagioNegociacao);
	}
	
	public EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao) {
		EstagioNegociacao estagioNegociacaoSalvo = buscarEstagioExistente(id);
		BeanUtils.copyProperties(estagioNegociacao, estagioNegociacaoSalvo, "id");
		return estagioNegociacaoRepository.save(estagioNegociacaoSalvo);
	}
	
	private EstagioNegociacao buscarEstagioExistente(Long id) {
		Optional<EstagioNegociacao> estagioNegociacaoSalvo = estagioNegociacaoRepository.findById(id);
		if (!estagioNegociacaoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return estagioNegociacaoSalvo.get();
	}
	
	private void estagioNegociacaoValidar(EstagioNegociacao fonteNegociacao) {
		if (fonteNegociacao == null) {
			throw new UsuarioInativaException();
		}
	}
}
