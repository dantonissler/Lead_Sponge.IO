package com.leadsponge.leadsponge.IO.serviceTestes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.leadsponge.IO.LeadSpongeApiApplication;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.services.CampanhaService;

//TODO validar aqui todas as regras que não passam pelo repository

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LeadSpongeApiApplication.class)
public class CampanhaServiceTestes {

	@Autowired
	private CampanhaService service;

	@Test
	public void quandoCriar_percistirOsDados() {
		Campanha campanha = new Campanha(null, "Nome de uma campanha", "Descrição de uma campanha");
		this.service.save(campanha);
		assertThat(campanha.getId()).isNotNull();
		assertThat(campanha.getNome()).isEqualTo("Nome de uma campanha");
		assertThat(campanha.getDescricao()).isEqualTo("Descrição de uma campanha");
	}

//    @Test
//    public void whenCreate_thenPersistData () {
//
//        Student student = new Student("Marcos", "marcos.teste@email.com");
//        this.studentRepository.save(student);
//
//        assertThat(student.getId()).isNotNull();
//        assertThat(student.getName()).isEqualTo("Marcos");
//        assertThat(student.getEmail()).isEqualTo("marcos.teste@email.com");
//
//    }
//
//    @Test
//    public void whenDelete_thenRemoveData () {
//        Student student = new Student("Marcos", "marcos.teste@email.com");
//        this.studentRepository.save(student);
//        studentRepository.delete(student);
//        assertThat(studentRepository.findById(student.getId())).isEmpty();
//
//    }
//
//    @Test
//    public void whenUpdate_thenChangeAndPersistData () {
//        Student student = new Student("Marcos", "marcos.teste@email.com");
//        this.studentRepository.save(student);
//
//        student = new Student("Marcos Dois", "marcos.testedois@email.com");
//        this.studentRepository.save(student);
//
//        student = studentRepository.findById(student.getId()).orElse(null);
//
//        assertThat(student.getName()).isEqualTo("Marcos Dois");
//        assertThat(student.getEmail()).isEqualTo("marcos.testedois@email.com");
//    }
//
//    @Test
//    public void whenFindByNameIgnoreCaseContaining_thenIgnoreCase () {
//        Student student1 = new Student("Marcos", "marcos.teste@email.com");
//        Student student2 = new Student("marcos", "marcos.teste2@email.com");
//
//        this.studentRepository.save(student1);
//        this.studentRepository.save(student2);
//
//        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("marcos");
//
//        assertThat(studentList.size()).isEqualTo(2);
//
//    }
//
//    @Test
//    public void whenNotEmptyName_thenNoConstraintViolations () {
//        Exception exception = assertThrows(
//                ConstraintViolationException.class,
//                () -> studentRepository.save(new Student("", "email@gmail.com")));
//
//        assertTrue(exception.getMessage().contains("O campo nome do estudante é obrigatório"));
//    }
//
//    @Test
//    public void whenNotEmptyEmail_thenNoConstraintViolations () {
//        Exception exception = assertThrows(
//                ConstraintViolationException.class,
//                () -> studentRepository.save(new Student("Marcos", "")));
//
//        assertTrue(exception.getMessage().contains("O campo email é obrigatório"));
//    }
//
//    @Test
//    public void whenValidEmail_thenNoConstraintViolations () {
//        Exception exception = assertThrows(
//                ConstraintViolationException.class,
//                () -> studentRepository.save(new Student("Marcos", "wrongemail.email")));
//
//        assertTrue(exception.getMessage().contains("O email deve ser válido"));
//    }

}
