package br.com.blinkdev.leadsponge.end_points.contact.repository;

import br.com.blinkdev.leadsponge.end_points.contact.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long>, ContactRepositoryQuery {
}
