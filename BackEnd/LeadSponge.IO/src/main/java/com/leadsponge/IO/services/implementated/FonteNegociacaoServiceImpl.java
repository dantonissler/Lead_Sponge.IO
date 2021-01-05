package com.leadsponge.IO.services.implementated;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.fonteNegociacao.FonteNegociacaoRepository;
import com.leadsponge.IO.services.FonteNegociacaoService;

@Service
public class FonteNegociacaoServiceImpl implements FonteNegociacaoService {

	@Autowired
	private FonteNegociacaoRepository fonteNegociacaoRepository;

	@Override
	public FonteNegociacao save(FonteNegociacao fonteNegociacao) {
		fonteNegociacaoValidar(fonteNegociacao);
		return fonteNegociacaoRepository.save(fonteNegociacao);
	}

	@Override
	public FonteNegociacao atualizar(Long id, FonteNegociacao fonteNegociacao) {
		FonteNegociacao fonteNegociacaoSalvo = buscarCampanhaExistente(id);
		BeanUtils.copyProperties(fonteNegociacao, fonteNegociacaoSalvo, "id");
		return fonteNegociacaoRepository.save(fonteNegociacaoSalvo);
	}

	private FonteNegociacao buscarCampanhaExistente(Long id) {
		Optional<FonteNegociacao> fonteNegociacaoSalvo = fonteNegociacaoRepository.findById(id);
		if (!fonteNegociacaoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return fonteNegociacaoSalvo.get();
	}

	private void fonteNegociacaoValidar(FonteNegociacao fonteNegociacao) {
		if (fonteNegociacao == null) {
			throw new UsuarioInativaException();
		}
	}
}
