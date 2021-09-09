package br.com.blinkdev.leadsponge.endPoints.negociacao.service;

import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.EstatusNegociacao;
import br.com.blinkdev.leadsponge.endPoints.negociacao.filter.NegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface NegociacaoService {
	void calculo(Long id);

	void atribuirPropMP(Long id, MotivoPerdaEntity motivoPerda);

	void atualizarPropriedadeEstatus(Long id, EstatusNegociacao estatus);

	void atualizarPropriedadeDataFim(Long id, LocalDateTime data);

	void atualizarPropriedadeEstagio(Long id, EstagioNegociacaoEntity estagio);

	void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao);

	NegociacaoEntity salvar(NegociacaoEntity negociacao);

	NegociacaoEntity atualizar(Long id, NegociacaoEntity negociacao);

	NegociacaoEntity deletar(Long id);

	NegociacaoEntity detalhar(Long id);

	Page<NegociacaoEntity> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable);

}
