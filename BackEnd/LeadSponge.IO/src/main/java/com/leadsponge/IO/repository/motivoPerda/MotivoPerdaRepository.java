package com.leadsponge.IO.repository.motivoPerda;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.motivoPerda.MotivoPerda;

public interface MotivoPerdaRepository extends JpaRepository<MotivoPerda, Long>, MotivoPerdaRepositoryQuery {

}
