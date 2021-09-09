package br.com.blinkdev.leadsponge.endPoints.email.repository;

import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.email.filter.EmailFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepositoryQuery {
	Page<EmailEntity> filtrar(EmailFilter emailFilter, Pageable pageable);
}
