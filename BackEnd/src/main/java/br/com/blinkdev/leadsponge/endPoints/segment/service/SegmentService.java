package br.com.blinkdev.leadsponge.endPoints.segment.service;

import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endPoints.segment.filter.SegmentFilter;
import br.com.blinkdev.leadsponge.endPoints.segment.model.SegmentModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SegmentService {
    SegmentModel getById(Long id);

    PagedModel<SegmentModel> searchWithFilters(SegmentFilter segmentFilter, Pageable pageable);

    SegmentModel save(SegmentEntity segmentEntity);

    SegmentModel patch(Long id, Map<Object, Object> fields);

    SegmentModel delete(Long id);
}
