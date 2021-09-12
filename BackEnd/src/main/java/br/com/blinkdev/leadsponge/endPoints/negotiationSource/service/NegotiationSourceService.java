package br.com.blinkdev.leadsponge.endPoints.negotiationSource.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.filter.NegotiationSourceFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NegotiationSourceService {
    NegotiationSourceEntity salvar(NegotiationSourceEntity fonteNegociacao);

    NegotiationSourceEntity atualizar(Long id, NegotiationSourceEntity fonteNegociacao);

    NegotiationSourceEntity deletar(Long id);

    NegotiationSourceEntity detalhar(Long id);

    Page<NegotiationSourceEntity> filtrar(NegotiationSourceFilter fonteNegociacaoFilter, Pageable pageable);
}
