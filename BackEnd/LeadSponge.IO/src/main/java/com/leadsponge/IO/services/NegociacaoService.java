package com.leadsponge.IO.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.models.negociacao.EstatusNegociacao;
import com.leadsponge.IO.models.negociacao.Negociacao;

@Service
public interface NegociacaoService {
	public void calculo(Long id);

	public void atribuirPropMP(Long id, MotivoPerda motivoPerda);

	public void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus);

	public void atualizarPropriedadeDataFim(Long id, Date data);

	public void atualizarPropriedadeEstagio(Long id, EstagioNegociacao estagio);

	public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao);

	public Negociacao save(Negociacao negociacao);

	public Negociacao atualizar(Long id, Negociacao negociacao);

}
