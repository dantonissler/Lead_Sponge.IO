package com.leadsponge.IO.models.audit;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdBy", "updatedBy" }, allowGetters = true)
public abstract class UserDateAudit extends DateAudit {

	@CreatedBy
	@Column(nullable = false, updatable = false)
	private String createdByUser;

	@LastModifiedBy
	@Column(nullable = false)
	private String modifiedByUser;

	@PrePersist
	public void prePersist() {
		String createdByUser = getUsernameOfAuthenticatedUser();
		this.createdByUser = createdByUser;
		this.modifiedByUser = createdByUser;
	}

	@PreUpdate
	public void preUpdate() {
		String modifiedByUser = getUsernameOfAuthenticatedUser();
		this.modifiedByUser = modifiedByUser;
	}

	private String getUsernameOfAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			return "Sistema";
		}
		String username = authentication.getName();
		return username;
	}

}