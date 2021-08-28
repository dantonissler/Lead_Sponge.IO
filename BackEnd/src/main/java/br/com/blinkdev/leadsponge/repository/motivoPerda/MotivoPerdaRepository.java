package br.com.blinkdev.leadsponge.repository.motivoPerda;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoPerdaRepository extends JpaRepository<MotivoPerda, Long>, MotivoPerdaRepositoryQuery {

}
