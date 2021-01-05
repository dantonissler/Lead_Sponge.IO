package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.motivoPerda.MotivoPerda;

@Service
public interface MotivoPerdaService {
	public MotivoPerda save(MotivoPerda motivoPerda);

	public MotivoPerda atualizar(Long id, MotivoPerda motivoPerda);
}
