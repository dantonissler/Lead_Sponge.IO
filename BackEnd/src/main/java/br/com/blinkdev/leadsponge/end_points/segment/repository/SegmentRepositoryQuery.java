package br.com.blinkdev.leadsponge.end_points.segment.repository;

import br.com.blinkdev.leadsponge.end_points.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.end_points.segment.filter.SegmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepositoryQuery {
    Page<SegmentEntity> filtrar(SegmentFilter segmentoFilter, Pageable pageable);
}
