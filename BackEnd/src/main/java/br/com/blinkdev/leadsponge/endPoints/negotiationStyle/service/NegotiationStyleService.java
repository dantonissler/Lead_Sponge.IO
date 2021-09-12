package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.filter.NegotiationStyleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NegotiationStyleService {
    NegotiationStyleEntity salvar(NegotiationStyleEntity estagioNegociacao);

    NegotiationStyleEntity atualizar(Long id, NegotiationStyleEntity estagioNegociacao);

    NegotiationStyleEntity deletar(Long id);

    NegotiationStyleEntity detalhar(Long id);

    Page<NegotiationStyleEntity> filtrar(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable);
}
