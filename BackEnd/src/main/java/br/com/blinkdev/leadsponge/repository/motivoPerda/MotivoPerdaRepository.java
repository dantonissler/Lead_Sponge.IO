package br.com.blinkdev.leadsponge.repository.motivoPerda;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoPerdaRepository extends JpaRepository<MotivoPerda, Long>, MotivoPerdaRepositoryQuery {

}
