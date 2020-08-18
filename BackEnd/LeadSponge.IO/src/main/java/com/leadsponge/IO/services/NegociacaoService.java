package com.leadsponge.IO.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.models.negociacao.EstatusNegociacao;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.negociacaoProduto.TipoReincidencia;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.repository.negociacao.NegociacaoRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class NegociacaoService extends ErroMessage{

	@Autowired
	private NegociacaoRepository repository;

	@Autowired
	private ClienteRepository clienteR;

	/**
	 * TODO: Criar formula para calcular os valores da negociação.
	 */

	public void calculo(Long id) {
		Negociacao negociacao = buscarNegociacaoExistente(id);
		BigDecimal somaTotal = negociacao.getNegociacaoProdutos().stream().map(total -> total.getTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal somaMensal = negociacao.getNegociacaoProdutos().stream()
				.filter(mensal -> mensal.getReincidencia().equals(TipoReincidencia.RECORRENTE))
				.map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal somaUnico = negociacao.getNegociacaoProdutos().stream()
				.filter(mensal -> mensal.getReincidencia().equals(TipoReincidencia.UNICO))
				.map(unico -> unico.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		negociacao.setValorMensal(somaMensal);
		negociacao.setValorUnico(somaUnico);
		negociacao.setValorTotal(somaTotal);

		repository.save(negociacao);
	}

	public void atribuirPropMP(Long id, MotivoPerda motivoPerda) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setMotivoPerda(motivoPerda);
		negociacaoSalva.setEstatus(EstatusNegociacao.PERDIDA);
		repository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		if (negociacaoSalva.getEstatus() == EstatusNegociacao.PERDIDA)
			negociacaoSalva.setMotivoPerda(null);
		negociacaoSalva.setEstatus(estatus);
		repository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeDataFim(Long id, Date data) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setDataPrevistaEncerramento(data);
		repository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeEstagio(Long id, EstagioNegociacao estagio) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setEstagio(estagio);
		repository.save(negociacaoSalva);
	}

	public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
		Negociacao negociacaoSalva = buscarNegociacaoExistente(id);
		negociacaoSalva.setAvaliacao(avaliacao);
		repository.save(negociacaoSalva);
	}

	public Negociacao save(Negociacao negociacao) {
		negociacaoValidar(negociacao);
		return repository.save(negociacao);
	}

	public Negociacao atualizar(Long id, Negociacao negociacao) {
		Negociacao novaNegociacao = buscarNegociacaoExistente(id);
		BeanUtils.copyProperties(negociacao, novaNegociacao, "id");
		return repository.save(novaNegociacao);
	}

	private Negociacao buscarNegociacaoExistente(Long id) {
		Optional<Negociacao> negociacaoPerdaSalvo = repository.findById(id);
		if (!negociacaoPerdaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return negociacaoPerdaSalvo.get();
	}

	private void negociacaoValidar(Negociacao negociacao) {
		if (negociacao == null) {
			throw new UsuarioInativaException();
		}
		clienteR.findById(negociacao.getCliente().getId())
				.orElseThrow(() -> notFouldId(negociacao.getCliente().getId(), "o cliente"));
	}
}
