package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.service;

import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.filter.ReasonForLossFilter;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.model.ReasonForLossModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ReasonForLossService {

    ReasonForLossModel getById(Long id);

    PagedModel<ReasonForLossModel> searchWithFilters(ReasonForLossFilter reasonForLossFilter, Pageable pageable);

    ReasonForLossModel save(ReasonForLossEntity reasonForLossEntity);

    ReasonForLossModel patch(Long id, Map<Object, Object> fields);

    ReasonForLossModel delete(Long id);
}
