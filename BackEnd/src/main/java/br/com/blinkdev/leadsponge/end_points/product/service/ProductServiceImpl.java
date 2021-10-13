package br.com.blinkdev.leadsponge.end_points.product.service;


import br.com.blinkdev.leadsponge.end_points.product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.end_points.product.filter.ProductFilter;
import br.com.blinkdev.leadsponge.end_points.product.model.ProductModel;
import br.com.blinkdev.leadsponge.end_points.product.model_assembler.ProductModelAssembler;
import br.com.blinkdev.leadsponge.end_points.product.repository.ProductRepository;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl extends ErroMessage implements ProductService {
    private final ProductRepository productRepository;
    private final ProductModelAssembler productModelAssembler;
    private final PagedResourcesAssembler<ProductEntity> assembler;

    @Override
    public ProductModel getById(Long id) {
        log.info("ProductService - getById");
        return productRepository.findById(id).map(productModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "a produto"));
    }

    @Override
    public PagedModel<ProductModel> searchWithFilters(ProductFilter produtoFilter, Pageable pageable) {
        log.info("ProductService - searchWithFilters");
        return assembler.toModel(productRepository.filtrar(produtoFilter, pageable), productModelAssembler);
    }

    @Override
    @Transactional
    public ProductModel save(ProductEntity produto) {
        log.info("ProductService - save");
        return productModelAssembler.toModel(productRepository.save(produto));
    }

    @Override
    @Transactional
    public ProductModel patch(Long id, Map<Object, Object> fields) {
        log.info("ProductService - patch");
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> notFouldId(id, "[product]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ProductEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, productEntity, value);
        });
        return save(productEntity);
    }

    @Override
    @Transactional
    public ProductModel delete(Long id) {
        log.info("ProductService - delete");
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> notFouldId(id, "[product]"));
        productRepository.deleteById(id);
        return productModelAssembler.toModel(productEntity);
    }

    @Override
    public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade) {
        log.info("ProductService - atualizarPropriedadeVisibilidade");
        ProductEntity produtoSalva = productRepository.findById(id).orElseThrow(() -> notFouldId(id, "[product]"));
        produtoSalva.setVisibility(visibilidade);
        productRepository.save(produtoSalva);
    }
}
