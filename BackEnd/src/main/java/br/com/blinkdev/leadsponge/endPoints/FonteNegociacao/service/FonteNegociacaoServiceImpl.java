package br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.filter.FonteNegociacaoFilter;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.repository.FonteNegociacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FonteNegociacaoServiceImpl extends ErroMessage implements FonteNegociacaoService {

	@Autowired
	private FonteNegociacaoRepository repository;

	@Override
	public FonteNegociacaoEntity salvar(FonteNegociacaoEntity fonteNegociacao) {
		return repository.save(fonteNegociacao);
	}

	@Override
	public FonteNegociacaoEntity atualizar(Long id, FonteNegociacaoEntity fonteNegociacao) {
		FonteNegociacaoEntity fonteNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
		BeanUtils.copyProperties(fonteNegociacao, fonteNegociacaoSalvo, "id");
		return repository.save(fonteNegociacaoSalvo);
	}

	@Override
	public FonteNegociacaoEntity deletar(Long id) {
		FonteNegociacaoEntity fonteNegociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
		repository.deleteById(id);
		return fonteNegociacao;
	}

	@Override
	public FonteNegociacaoEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
	}

	@Override
	public Page<FonteNegociacaoEntity> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
		return repository.filtrar(fonteNegociacaoFilter, pageable);
	}
}
