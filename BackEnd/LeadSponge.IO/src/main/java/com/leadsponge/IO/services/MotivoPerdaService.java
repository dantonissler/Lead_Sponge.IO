package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.motivoPerda.MotivoPerdaRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class MotivoPerdaService {
	@Autowired
	private MotivoPerdaRepository segmentoRepository;
	
	public MotivoPerda save(MotivoPerda motivoPerda) {
		motivoPerdaValidar(motivoPerda);
		return segmentoRepository.save(motivoPerda);
	}
	
	public MotivoPerda atualizar(Long id, MotivoPerda motivoPerda) {
		MotivoPerda novomotivoPerda = buscarSegmentoExistente(id);
		BeanUtils.copyProperties(motivoPerda, novomotivoPerda, "id");
		return segmentoRepository.save(novomotivoPerda);
	}
	
	private MotivoPerda buscarSegmentoExistente(Long id) {
		Optional<MotivoPerda> motivoPerdaSalvo = segmentoRepository.findById(id);
		if (!motivoPerdaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return motivoPerdaSalvo.get();
	}
	
	private void motivoPerdaValidar(MotivoPerda segmento) {
		if (segmento == null) {
			throw new UsuarioInativaException();
		}
	}
}
