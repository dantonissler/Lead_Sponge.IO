//package com.leadsponge.IO.data;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.leadsponge.IO.security.models.Role;
//import com.leadsponge.IO.security.repository.RoleRepository;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration
//@Slf4j
//class LoadDatabase {
//
//	@Bean
//	CommandLineRunner initDatabase(RoleRepository repository) {
//		return args -> {
//			repository.save(new Role("ADMIN"));
//		};
//	}
//}
