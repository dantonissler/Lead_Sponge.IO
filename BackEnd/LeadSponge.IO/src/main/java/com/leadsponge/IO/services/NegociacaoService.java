package com.leadsponge.IO.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.models.negociacao.EstatusNegociacao;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.repository.negociacao.NegociacaoRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class NegociacaoService {

	@Autowired
	private final NegociacaoRepository negociacaoRepository;

	public NegociacaoService(NegociacaoRepository negociacaoRepository) {
		this.negociacaoRepository = negociacaoRepository;
	}

	public void atribuirPropMP(Long id, MotivoPerda motivoPerda) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setMotivoPerda(motivoPerda);
		negociacaoSalva.setEstatus(EstatusNegociacao.PERDIDA);
		negociacaoRepository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		if (negociacaoSalva.getEstatus() == EstatusNegociacao.PERDIDA)
			negociacaoSalva.setMotivoPerda(null);
		negociacaoSalva.setEstatus(estatus);
		negociacaoRepository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeDataFim(Long id, Date data) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setDataPrevistaEncerramento(data);
		negociacaoRepository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeEstagio(Long id, EstagioNegociacao estagio) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setEstagio(estagio);
		negociacaoRepository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setAvaliacao(avaliacao);
		negociacaoRepository.save(negociacaoSalva);
	}

	public Negociacao save(Negociacao negociacao) {
		negociacaoValidar(negociacao);
		return negociacaoRepository.save(negociacao);
	}

	public Negociacao atualizar(Long id, Negociacao negociacao) {
		Negociacao novaNegociacao = buscarNegociacaoExistente(id);
		BeanUtils.copyProperties(negociacao, novaNegociacao, "id");
		return negociacaoRepository.save(novaNegociacao);
	}

	private Negociacao buscarNegociacaoExistente(Long id) {
		Optional<Negociacao> negociacaoPerdaSalvo = negociacaoRepository.findById(id);
		if (!negociacaoPerdaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return negociacaoPerdaSalvo.get();
	}

	private void negociacaoValidar(Negociacao negociacao) {
		if (negociacao == null) {
			throw new UsuarioInativaException();
		}
	}
}
