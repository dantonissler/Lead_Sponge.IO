package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.service;

import br.com.blinkdev.leadsponge.endPoints.Product.repository.ProductRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiation.repository.NegotiationRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiation.service.NegotiationService;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.enumeration.DiscountType;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.repository.NegotiationProductRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NegotiationProductServiceImpl extends ErroMessage implements NegotiationProductService {

    @Autowired
    private NegotiationProductRepository repository;

    @Autowired
    private ProductRepository produtoR;

    @Autowired
    private NegotiationRepository negociacaoR;

    @Autowired
    private NegotiationService negotiationService;

    @Override
    public NegotiationProductEntity salvar(NegotiationProductEntity nProduto) {
        produtoR.findById(nProduto.getProduto().getId()).orElseThrow(() -> notFouldId(nProduto.getProduto().getId(), "o produto"));
        negociacaoR.findById(nProduto.getNegociacao().getId()).orElseThrow(() -> notFouldId(nProduto.getNegociacao().getId(), "a negociação do produto"));
        valorTotal(nProduto);
        negotiationService.calculo(nProduto.getNegociacao().getId());
        return repository.save(nProduto);
    }

    @Override
    public NegotiationProductEntity atualizar(Long id, NegotiationProductEntity negociacaoProduto) {
        NegotiationProductEntity fonteNegociacaoProduto = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
        valorTotal(negociacaoProduto);
        BeanUtils.copyProperties(negociacaoProduto, fonteNegociacaoProduto, "id");
        negotiationService.calculo(negociacaoProduto.getNegociacao().getId());
        return repository.save(fonteNegociacaoProduto);
    }

    private NegotiationProductEntity valorTotal(NegotiationProductEntity negociacaoProduto) {
        try {
            BigDecimal valor = negociacaoProduto.getValor();
            Integer qtd = negociacaoProduto.getQuantidade();
            BigDecimal total = valor.multiply(BigDecimal.valueOf(qtd.longValue()));
            if (negociacaoProduto.getTipoDesconto() == DiscountType.PORCENTAGEM && negociacaoProduto.getTemDesconto()) {
                BigDecimal desconto = BigDecimal.valueOf(1).subtract(negociacaoProduto.getDesconto().divide(BigDecimal.valueOf(100)));
                negociacaoProduto.setTotal(total.multiply(desconto));
            } else if (negociacaoProduto.getTipoDesconto() == DiscountType.VALOR && negociacaoProduto.getTemDesconto()) {
                negociacaoProduto.setTotal(total.subtract(negociacaoProduto.getDesconto()));
            } else if (!negociacaoProduto.getTemDesconto()) {
                negociacaoProduto.setDesconto(null);
                negociacaoProduto.setTipoDesconto(null);
                negociacaoProduto.setTotal(total);
            }
        } catch (Exception e) {
            throw notFould("o produto");
        }
        return negociacaoProduto;
    }

    @Override
    public NegotiationProductEntity deletar(Long id) {
        NegotiationProductEntity megociacaoProduto = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
        repository.deleteById(id);
        return megociacaoProduto;
    }

    @Override
    public NegotiationProductEntity detalhar(Long id) {
        // TODO fazer as devidas validações
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação do produto"));
    }

    @Override
    public Page<NegotiationProductEntity> filtrar(NegotiationProductFilter negociacaoProdutoFilter, Pageable pageable) {
        return repository.filtrar(negociacaoProdutoFilter, pageable);
    }
}
