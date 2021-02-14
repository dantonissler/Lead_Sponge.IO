package com.leadsponge.IO.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.models.negociacao.EstatusNegociacao;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.repository.Filter.NegociacaoFilter;

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
