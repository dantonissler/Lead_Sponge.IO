package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.services.FonteNegociacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.repository.Filter.FonteNegociacaoFilter;
import br.com.blinkdev.leadsponge.repository.fonteNegociacao.FonteNegociacaoRepository;

@Service
public class FonteNegociacaoServiceImpl extends ErroMessage implements FonteNegociacaoService {

	@Autowired
	private FonteNegociacaoRepository repository;

	@Override
	public FonteNegociacao salvar(FonteNegociacao fonteNegociacao) {
		return repository.save(fonteNegociacao);
	}

	@Override
	public FonteNegociacao atualizar(Long id, FonteNegociacao fonteNegociacao) {
		FonteNegociacao fonteNegociacaoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
		BeanUtils.copyProperties(fonteNegociacao, fonteNegociacaoSalvo, "id");
		return repository.save(fonteNegociacaoSalvo);
	}

	@Override
	public FonteNegociacao deletar(Long id) {
		FonteNegociacao fonteNegociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
		repository.deleteById(id);
		return fonteNegociacao;
	}

	@Override
	public FonteNegociacao detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
	}

	@Override
	public Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
		return repository.filtrar(fonteNegociacaoFilter, pageable);
	}
}
