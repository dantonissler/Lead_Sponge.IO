package br.com.blinkdev.leadsponge.endPoints.Product.service;


import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endPoints.Product.filter.ProductFilter;
import br.com.blinkdev.leadsponge.endPoints.Product.repository.ProductRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ErroMessage implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductEntity salvar(ProductEntity produto) {
        return repository.save(produto);
    }

    @Override
    public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade) {
        ProductEntity produtoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
        produtoSalva.setVisibilidade(visibilidade);
        repository.save(produtoSalva);
    }

    @Override
    public ProductEntity atualizar(Long id, ProductEntity produto) {
        ProductEntity produtoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
        BeanUtils.copyProperties(produto, produtoSalva, "id");
        return repository.save(produtoSalva);
    }

    @Override
    public Page<ProductEntity> filtrar(ProductFilter produtoFilter, Pageable pageable) {
        return repository.filtrar(produtoFilter, pageable);
    }

    @Override
    public ProductEntity deletar(Long id) {
        ProductEntity produtoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
        repository.deleteById(id);
        return produtoSalvo;
    }

    @Override
    public ProductEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
    }
}
