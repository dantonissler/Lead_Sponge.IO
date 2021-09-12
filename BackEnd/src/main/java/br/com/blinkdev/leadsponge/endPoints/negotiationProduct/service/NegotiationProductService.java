package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NegotiationProductService {
    NegotiationProductEntity salvar(NegotiationProductEntity nProduto);

    NegotiationProductEntity atualizar(Long id, NegotiationProductEntity negociacaoProduto);

    NegotiationProductEntity deletar(Long id);

    NegotiationProductEntity detalhar(Long id);

    Page<NegotiationProductEntity> filtrar(NegotiationProductFilter negociacaoProdutoFilter, Pageable pageable);
}
