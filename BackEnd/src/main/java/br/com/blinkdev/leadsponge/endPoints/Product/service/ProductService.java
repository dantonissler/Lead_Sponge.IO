package br.com.blinkdev.leadsponge.endPoints.Product.service;

import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endPoints.Product.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductEntity salvar(ProductEntity produto);

    void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);

    ProductEntity atualizar(Long id, ProductEntity produto);

    ProductEntity deletar(Long id);

    ProductEntity detalhar(Long id);

    Page<ProductEntity> filtrar(ProductFilter negociacaoFilter, Pageable pageable);
}
