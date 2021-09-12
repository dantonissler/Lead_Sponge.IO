package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.service;

import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.filter.ReasonForLossFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReasonForLossService {
    ReasonForLossEntity salvar(ReasonForLossEntity motivoPerda);

    ReasonForLossEntity atualizar(Long id, ReasonForLossEntity motivoPerda);

    ReasonForLossEntity deletar(Long id);

    ReasonForLossEntity detalhar(Long id);

    Page<ReasonForLossEntity> filtrar(ReasonForLossFilter motivoPerdaFilter, Pageable pageable);
}
