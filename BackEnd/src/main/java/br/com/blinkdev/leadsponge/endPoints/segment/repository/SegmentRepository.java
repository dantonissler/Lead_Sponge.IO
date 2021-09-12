package br.com.blinkdev.leadsponge.endPoints.segment.repository;

import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<SegmentEntity, Long>, SegmentRepositoryQuery {

}
