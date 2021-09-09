package br.com.blinkdev.leadsponge.endPoints.motivoPerda.repository;

import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoPerdaRepository extends JpaRepository<MotivoPerdaEntity, Long>, MotivoPerdaRepositoryQuery {

}
