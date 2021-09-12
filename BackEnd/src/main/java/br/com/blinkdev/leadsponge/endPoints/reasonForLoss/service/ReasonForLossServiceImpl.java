package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.service;

import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.filter.ReasonForLossFilter;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.repository.ReasonForLossRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReasonForLossServiceImpl extends ErroMessage implements ReasonForLossService {

    @Autowired
    private ReasonForLossRepository repository;

    @Override
    public ReasonForLossEntity salvar(ReasonForLossEntity motivoPerda) {
        return repository.save(motivoPerda);
    }

    @Override
    public ReasonForLossEntity atualizar(Long id, ReasonForLossEntity motivoPerda) {
        ReasonForLossEntity novomotivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
        BeanUtils.copyProperties(motivoPerda, novomotivoPerda, "id");
        return repository.save(novomotivoPerda);
    }

    @Override
    public ReasonForLossEntity deletar(Long id) {
        ReasonForLossEntity motivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
        repository.deleteById(id);
        return motivoPerda;
    }

    @Override
    public ReasonForLossEntity detalhar(Long id) {
        // TODO fazer as devidas validações
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a motivo da negociação "));
    }

    @Override
    public Page<ReasonForLossEntity> filtrar(ReasonForLossFilter motivoPerdaFilter, Pageable pageable) {
        return repository.filtrar(motivoPerdaFilter, pageable);
    }
}
