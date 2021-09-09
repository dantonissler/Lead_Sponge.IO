package br.com.blinkdev.leadsponge.endPoints.negociacao.service;

import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.EstatusNegociacao;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.TipoReincidencia;
import br.com.blinkdev.leadsponge.endPoints.negociacao.filter.NegociacaoFilter;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.cliente.repository.ClienteRepository;
import br.com.blinkdev.leadsponge.endPoints.negociacao.repository.NegociacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class NegociacaoServiceImpl extends ErroMessage implements NegociacaoService {

	@Autowired
	private NegociacaoRepository repository;

	@Autowired
	private ClienteRepository clienteR;

	/**
	 * TODO: Criar formula para calcular os valores da negociação.
	 */
	@Override
	public void calculo(Long id) {
		NegociacaoEntity negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		BigDecimal somaTotal = negociacao.getNegociacaoProdutos().stream().map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal somaMensal = negociacao.getNegociacaoProdutos().stream().filter(mensal -> mensal.getReincidencia().equals(TipoReincidencia.RECORRENTE)).map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal somaUnico = negociacao.getNegociacaoProdutos().stream().filter(mensal -> mensal.getReincidencia().equals(TipoReincidencia.UNICO)).map(unico -> unico.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		negociacao.setValorMensal(somaMensal);
		negociacao.setValorUnico(somaUnico);
		negociacao.setValorTotal(somaTotal);
		repository.save(negociacao);
	}

	@Override
	public void atribuirPropMP(Long id, MotivoPerdaEntity motivoPerda) {
		NegociacaoEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setMotivoPerda(motivoPerda);
		negociacaoSalva.setEstatus(EstatusNegociacao.PERDIDA);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus) {
		NegociacaoEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		if (negociacaoSalva.getEstatus() == EstatusNegociacao.PERDIDA)
			negociacaoSalva.setMotivoPerda(null);
		negociacaoSalva.setEstatus(estatus);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeDataFim(Long id, LocalDateTime data) {
		NegociacaoEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setDataPrevistaEncerramento(data);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeEstagio(Long id, EstagioNegociacaoEntity estagio) {
		NegociacaoEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setEstagio(estagio);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
		NegociacaoEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setAvaliacao(avaliacao);
		repository.save(negociacaoSalva);
	}

	@Override
	public NegociacaoEntity salvar(NegociacaoEntity negociacao) {
		return repository.save(negociacao);
	}

	@Override
	public NegociacaoEntity atualizar(Long id, NegociacaoEntity negociacao) {
		NegociacaoEntity novaNegociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		clienteR.findById(negociacao.getCliente().getId()).orElseThrow(() -> notFouldId(negociacao.getCliente().getId(), "o cliente"));
		// TODO fazer as devidas validações
		BeanUtils.copyProperties(negociacao, novaNegociacao, "id");
		return repository.save(novaNegociacao);
	}

	@Override
	public NegociacaoEntity deletar(Long id) {
		NegociacaoEntity negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
		repository.deleteById(id);
		return negociacao;
	}

	@Override
	public NegociacaoEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
	}

	@Override
	public Page<NegociacaoEntity> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable) {
		// TODO fazer as devidas validações
		return repository.filtrar(negociacaoFilter, pageable);
	}
}
