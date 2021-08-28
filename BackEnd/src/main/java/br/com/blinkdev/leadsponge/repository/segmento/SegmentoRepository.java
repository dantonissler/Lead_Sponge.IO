package br.com.blinkdev.leadsponge.repository.segmento;

import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, Long>, SegmentoRepositoryQuery {

}
