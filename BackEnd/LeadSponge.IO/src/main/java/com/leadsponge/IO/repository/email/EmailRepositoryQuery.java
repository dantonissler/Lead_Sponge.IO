package com.leadsponge.IO.repository.email;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.repository.Filter.EmailFilter;

public interface EmailRepositoryQuery {
	public Page<Email> filtrar(EmailFilter emailFilter, Pageable pageable);
}
