package com.leadsponge.leadsponge.IO.jdbcTests;

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


@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LeadSpongeApiApplication.class)
public class CampanhaRepositoryTest {

	@Autowired
	private CampanhaRepository repository;

	@Test
	public void quandoCriar_ePercistirOsDados() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.repository.save(campanha);
		assertThat(campanha.getId()).isNotNull();
		assertThat(campanha.getNome()).isEqualTo("Nome de uma campanha");
		assertThat(campanha.getDescricao()).isEqualTo("Descrição de uma campanha");
	}

	@Test
	public void quandoRemover_eRemoverOsDados() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.repository.save(campanha);
		repository.delete(campanha);
		assertThat(repository.findById(campanha.getId())).isEmpty();
	}

}
