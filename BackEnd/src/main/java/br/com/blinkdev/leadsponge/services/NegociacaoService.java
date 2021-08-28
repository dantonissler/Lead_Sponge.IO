package br.com.blinkdev.leadsponge.services;

import java.util.Date;

import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.negociacao.EstatusNegociacao;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.NegociacaoFilter;

@Service
public interface NegociacaoService {
	void calculo(Long id);

	void atribuirPropMP(Long id, MotivoPerda motivoPerda);

	void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus);

	void atualizarPropriedadeDataFim(Long id, Date data);

	void atualizarPropriedadeEstagio(Long id, EstagioNegociacao estagio);

	void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao);

	Negociacao salvar(Negociacao negociacao);

	Negociacao atualizar(Long id, Negociacao negociacao);

	Negociacao deletar(Long id);

	Negociacao detalhar(Long id);

	Page<Negociacao> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable);

}
