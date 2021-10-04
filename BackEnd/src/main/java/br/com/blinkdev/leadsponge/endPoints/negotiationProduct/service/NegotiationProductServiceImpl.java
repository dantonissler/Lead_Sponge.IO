package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.service;

import br.com.blinkdev.leadsponge.endPoints.Product.repository.ProductRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiation.repository.NegotiationRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiation.service.NegotiationService;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.enumeration.DiscountType;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.model.NegotiationProductModel;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.modelAssembler.NegotiationProductModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.repository.NegotiationProductRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

@Slf4j
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

    @Autowired
    private NegotiationProductModelAssembler negotiationProductModelAssembler;

    @Autowired
    private PagedResourcesAssembler<NegotiationProductEntity> assembler;

    @Override
    public NegotiationProductModel getById(Long id) {
        log.info("NegotiationProductService - getById");
        return repository.findById(id).map(negotiationProductModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[negotiation product]"));
    }

    @Override
    public PagedModel<NegotiationProductModel> searchWithFilters(NegotiationProductFilter negociacaoProdutoFilter, Pageable pageable) {
        log.info("NegotiationProductService - searchWithFilters");
        return assembler.toModel(repository.filtrar(negociacaoProdutoFilter, pageable), negotiationProductModelAssembler);
    }

    @Override
    public NegotiationProductModel save(NegotiationProductEntity nProduto) {
        log.info("NegotiationProductService - save");
        produtoR.findById(nProduto.getProduto().getId()).orElseThrow(() -> notFouldId(nProduto.getProduto().getId(), "product"));
        negociacaoR.findById(nProduto.getNegociacao().getId()).orElseThrow(() -> notFouldId(nProduto.getNegociacao().getId(), "[negotiation product]"));
        valorTotal(nProduto);
        negotiationService.calculo(nProduto.getNegociacao().getId());
        return negotiationProductModelAssembler.toModel(repository.save(nProduto));
    }

    @Override
    public NegotiationProductModel patch(Long id, Map<Object, Object> fields) {
        log.info("NegotiationProductService - patch");
        NegotiationProductEntity negotiationProductEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation product]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(NegotiationProductEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, negotiationProductEntity, value);
        });
        return save(negotiationProductEntity);
    }

    @Override
    public NegotiationProductModel delete(Long id) {
        log.info("NegotiationProductService - delete");
        NegotiationProductEntity negotiationProductEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation product]"));
        repository.deleteById(id);
        return negotiationProductModelAssembler.toModel(negotiationProductEntity);
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
}
