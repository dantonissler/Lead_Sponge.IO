package br.com.blinkdev.leadsponge.endpoints.segment.repository;

import br.com.blinkdev.leadsponge.endpoints.segment.entity.SegmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<SegmentEntity, Long>, SegmentRepositoryQuery {

}
