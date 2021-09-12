package br.com.blinkdev.leadsponge.endPoints.negotiationSource.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.filter.NegotiationSourceFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.repository.NegotiationSourceRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NegotiationSourceServiceImpl extends ErroMessage implements NegotiationSourceService {

    @Autowired
    private NegotiationSourceRepository repository;

    @Override
    public NegotiationSourceEntity salvar(NegotiationSourceEntity fonteNegociacao) {
        return repository.save(fonteNegociacao);
    }

    @Override
    public NegotiationSourceEntity atualizar(Long id, NegotiationSourceEntity fonteNegociacao) {
        NegotiationSourceEntity fonteNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
        BeanUtils.copyProperties(fonteNegociacao, fonteNegociacaoSalvo, "id");
        return repository.save(fonteNegociacaoSalvo);
    }

    @Override
    public NegotiationSourceEntity deletar(Long id) {
        NegotiationSourceEntity fonteNegociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
        repository.deleteById(id);
        return fonteNegociacao;
    }

    @Override
    public NegotiationSourceEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
    }

    @Override
    public Page<NegotiationSourceEntity> filtrar(NegotiationSourceFilter fonteNegociacaoFilter, Pageable pageable) {
        return repository.filtrar(fonteNegociacaoFilter, pageable);
    }
}
