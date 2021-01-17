package com.leadsponge.leadsponge.IO.contatoTestes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LeadSpongeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testando o repositorio da Campanha")
public class Repository {

	@Autowired
	private CampanhaRepository repository;

	@AfterEach
	private void setUp() {
		repository.deleteAll();
	}

}
