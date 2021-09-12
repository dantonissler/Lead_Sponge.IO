package br.com.blinkdev.leadsponge.endPoints.phone.repository;

import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.filter.PhoneFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepositoryQuery {
    Page<PhoneEntity> filtrar(PhoneFilter telefoneFilter, Pageable pageable);
}
