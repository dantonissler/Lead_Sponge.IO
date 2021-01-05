package com.leadsponge.IO.services.implementated;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.motivoPerda.MotivoPerdaRepository;
import com.leadsponge.IO.services.MotivoPerdaService;

@Service
public class MotivoPerdaServiceImpl implements MotivoPerdaService {

	@Autowired
	private MotivoPerdaRepository repository;

	@Override
	public MotivoPerda save(MotivoPerda motivoPerda) {
		motivoPerdaValidar(motivoPerda);
		return repository.save(motivoPerda);
	}

	@Override
	public MotivoPerda atualizar(Long id, MotivoPerda motivoPerda) {
		MotivoPerda novomotivoPerda = buscarSegmentoExistente(id);
		BeanUtils.copyProperties(motivoPerda, novomotivoPerda, "id");
		return repository.save(novomotivoPerda);
	}

	private MotivoPerda buscarSegmentoExistente(Long id) {
		Optional<MotivoPerda> motivoPerdaSalvo = repository.findById(id);
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
