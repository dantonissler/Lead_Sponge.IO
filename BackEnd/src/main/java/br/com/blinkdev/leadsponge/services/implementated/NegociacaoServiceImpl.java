package br.com.blinkdev.leadsponge.services.implementated;

import java.math.BigDecimal;
import java.util.Date;

import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.negociacao.EstatusNegociacao;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.negociacao.TipoReincidencia;
import br.com.blinkdev.leadsponge.services.NegociacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.repository.Filter.NegociacaoFilter;
import br.com.blinkdev.leadsponge.repository.cliente.ClienteRepository;
import br.com.blinkdev.leadsponge.repository.negociacao.NegociacaoRepository;

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
		Negociacao negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		;
		BigDecimal somaTotal = negociacao.getNegociacaoProdutos().stream().map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal somaMensal = negociacao.getNegociacaoProdutos().stream().filter(mensal -> mensal.getReincidencia().equals(TipoReincidencia.RECORRENTE)).map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal somaUnico = negociacao.getNegociacaoProdutos().stream().filter(mensal -> mensal.getReincidencia().equals(TipoReincidencia.UNICO)).map(unico -> unico.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		negociacao.setValorMensal(somaMensal);
		negociacao.setValorUnico(somaUnico);
		negociacao.setValorTotal(somaTotal);
		repository.save(negociacao);
	}

	@Override
	public void atribuirPropMP(Long id, MotivoPerda motivoPerda) {

		Negociacao negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setMotivoPerda(motivoPerda);
		negociacaoSalva.setEstatus(EstatusNegociacao.PERDIDA);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus) {
		Negociacao negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		if (negociacaoSalva.getEstatus() == EstatusNegociacao.PERDIDA)
			negociacaoSalva.setMotivoPerda(null);
		negociacaoSalva.setEstatus(estatus);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeDataFim(Long id, Date data) {
		Negociacao negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setDataPrevistaEncerramento(data);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeEstagio(Long id, EstagioNegociacao estagio) {
		Negociacao negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setEstagio(estagio);
		repository.save(negociacaoSalva);
	}

	@Override
	public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
		Negociacao negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		negociacaoSalva.setAvaliacao(avaliacao);
		repository.save(negociacaoSalva);
	}

	@Override
	public Negociacao salvar(Negociacao negociacao) {
		return repository.save(negociacao);
	}

	@Override
	public Negociacao atualizar(Long id, Negociacao negociacao) {
		Negociacao novaNegociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
		clienteR.findById(negociacao.getCliente().getId()).orElseThrow(() -> notFouldId(negociacao.getCliente().getId(), "o cliente"));
		// TODO fazer as devidas validações
		BeanUtils.copyProperties(negociacao, novaNegociacao, "id");
		return repository.save(novaNegociacao);
	}

	@Override
	public Negociacao deletar(Long id) {
		Negociacao negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
		repository.deleteById(id);
		return negociacao;
	}

	@Override
	public Negociacao detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
	}

	@Override
	public Page<Negociacao> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable) {
		// TODO fazer as devidas validações
		return repository.filtrar(negociacaoFilter, pageable);
	}
}
