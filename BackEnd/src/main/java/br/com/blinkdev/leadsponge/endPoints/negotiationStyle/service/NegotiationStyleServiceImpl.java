package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.filter.NegotiationStyleFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.repository.NegotiationStyleRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NegotiationStyleServiceImpl extends ErroMessage implements NegotiationStyleService {

    @Autowired
    private NegotiationStyleRepository repository;

    @Override
    public NegotiationStyleEntity salvar(NegotiationStyleEntity estagioNegociacao) {
        return repository.save(estagioNegociacao);
    }

    @Override
    public NegotiationStyleEntity atualizar(Long id, NegotiationStyleEntity estagioNegociacao) {
        NegotiationStyleEntity estagioNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
        BeanUtils.copyProperties(estagioNegociacao, estagioNegociacaoSalvo, "id");
        return repository.save(estagioNegociacao);
    }

    @Override
    public Page<NegotiationStyleEntity> filtrar(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable) {
        return repository.filtrar(estagioNegociacaoFilter, pageable);
    }

    @Override
    public NegotiationStyleEntity deletar(Long id) {
        NegotiationStyleEntity estagioNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
        repository.deleteById(id);
        return estagioNegociacaoSalvo;
    }

    @Override
    public NegotiationStyleEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, " o estagio da negociação "));
    }
}
