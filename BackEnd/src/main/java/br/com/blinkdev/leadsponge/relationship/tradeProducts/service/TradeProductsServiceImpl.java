package br.com.blinkdev.leadsponge.relationship.tradeProducts.service;

import br.com.blinkdev.leadsponge.end_points.product.repository.ProductRepository;
import br.com.blinkdev.leadsponge.end_points.negotiation.repository.NegotiationRepository;
import br.com.blinkdev.leadsponge.end_points.negotiation.service.NegotiationService;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.enumeration.KindDiscount;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.filter.TradeProductsFilter;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.model.TradeProductsModel;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.modelAssembler.TradeProductsModelAssembler;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.repository.TradeProductsRepository;
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
public class TradeProductsServiceImpl extends ErroMessage implements TradeProductsService {

    @Autowired
    private TradeProductsRepository repository;

    @Autowired
    private ProductRepository produtoR;

    @Autowired
    private NegotiationRepository negociacaoR;

    @Autowired
    private NegotiationService negotiationService;

    @Autowired
    private TradeProductsModelAssembler negotiationProductModelAssembler;

    @Autowired
    private PagedResourcesAssembler<TradeProductsEntity> assembler;

    @Override
    public TradeProductsModel getById(Long id) {
        log.info("NegotiationProductService - getById");
        return repository.findById(id).map(negotiationProductModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[negotiation product]"));
    }

    @Override
    public PagedModel<TradeProductsModel> searchWithFilters(TradeProductsFilter negociacaoProdutoFilter, Pageable pageable) {
        log.info("NegotiationProductService - searchWithFilters");
        return assembler.toModel(repository.filtrar(negociacaoProdutoFilter, pageable), negotiationProductModelAssembler);
    }

    @Override
    public TradeProductsModel save(TradeProductsEntity nProduto) {
        log.info("NegotiationProductService - save");
        produtoR.findById(nProduto.getProduct().getId()).orElseThrow(() -> notFouldId(nProduto.getProduct().getId(), "product"));
        negociacaoR.findById(nProduto.getNegotiation().getId()).orElseThrow(() -> notFouldId(nProduto.getNegotiation().getId(), "[negotiation product]"));
        valorTotal(nProduto);
        negotiationService.calculateValues(nProduto.getNegotiation().getId());
        return negotiationProductModelAssembler.toModel(repository.save(nProduto));
    }

    @Override
    public TradeProductsModel patch(Long id, Map<Object, Object> fields) {
        log.info("NegotiationProductService - patch");
        TradeProductsEntity negotiationProductEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation product]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(TradeProductsEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, negotiationProductEntity, value);
        });
        return save(negotiationProductEntity);
    }

    @Override
    public TradeProductsModel delete(Long id) {
        log.info("NegotiationProductService - delete");
        TradeProductsEntity negotiationProductEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation product]"));
        repository.deleteById(id);
        return negotiationProductModelAssembler.toModel(negotiationProductEntity);
    }

    private TradeProductsEntity valorTotal(TradeProductsEntity negociacaoProduto) {
        try {
            BigDecimal valor = negociacaoProduto.getValor();
            Integer qtd = negociacaoProduto.getQuantidade();
            BigDecimal total = valor.multiply(BigDecimal.valueOf(qtd.longValue()));
            if (negociacaoProduto.getTipoDesconto() == KindDiscount.PORCENTAGEM && negociacaoProduto.getTemDesconto()) {
                BigDecimal desconto = BigDecimal.valueOf(1).subtract(negociacaoProduto.getDesconto().divide(BigDecimal.valueOf(100)));
                negociacaoProduto.setTotal(total.multiply(desconto));
            } else if (negociacaoProduto.getTipoDesconto() == KindDiscount.VALOR && negociacaoProduto.getTemDesconto()) {
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
