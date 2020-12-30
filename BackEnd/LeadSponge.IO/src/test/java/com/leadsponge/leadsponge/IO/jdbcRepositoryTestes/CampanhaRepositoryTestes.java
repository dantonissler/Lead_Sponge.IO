package com.leadsponge.leadsponge.IO.jdbcRepositoryTestes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;

// TODO validar aqui todas as regras que não passam pela service

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LeadSpongeApiApplication.class)
public class CampanhaRepositoryTestes {

	@Autowired
	private CampanhaRepository repository;

	@Test
	public void quandoCriar_percistirOsDados() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.repository.save(campanha);
		assertThat(campanha.getId()).isNotNull();
		assertThat(campanha.getNome()).isEqualTo("Nome de uma campanha");
		assertThat(campanha.getDescricao()).isEqualTo("Descrição de uma campanha");
	}

	@Test
	public void quandoRemover_removerOsDados() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.repository.save(campanha);
		repository.delete(campanha);
		assertThat(repository.findById(campanha.getId())).isEmpty();
	}

	@Test
	public void quandoUpdate_alterarEPersistirOsDados() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.repository.save(campanha);
		campanha = new Campanha(null, "Nome dois", "Descrição dois");
		this.repository.save(campanha);
		campanha = repository.findById(campanha.getId()).orElse(null);
		assertThat(campanha.getNome()).isEqualTo("Nome dois");
		assertThat(campanha.getDescricao()).isEqualTo("Descrição dois");
	}
/**
 * 	TODO não consegui implementar ainda por que para exibir o erro implementei a classe ValidationErrorDetails
 */
//	@Test
//	public void quandoNomeMenorDe4Letras_dispararExecao() {
//		Exception exception = assertThrows(ConstraintViolationException.class,
//				() -> repository.save(new Campanha(null, null, "Descrição")));
//		assertThat(exception.getCause());
//	}
//
//	@Test
//	public void quandoNomeMaiorDe50Letras_dispararExecao() {
//		Exception exception = assertThrows(ConstraintViolationException.class, () -> repository
//				.save(new Campanha(null, "dsadasdsaadsadasdsaadsadasdsaadsadasdsaadsadasdsaaa", "Descrição")));
//		assertTrue(exception.getMessage().contains("O nome deve ter entre 4 e 50 caracteres."));
//	}
}
