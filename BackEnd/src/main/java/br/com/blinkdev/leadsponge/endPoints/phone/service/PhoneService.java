package br.com.blinkdev.leadsponge.endPoints.phone.service;

import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.filter.PhoneFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PhoneService {
    PhoneEntity salvar(PhoneEntity contato);

    PhoneEntity atualizar(Long id, PhoneEntity contato);

    PhoneEntity deletar(Long id);

    PhoneEntity detalhar(Long id);

    Page<PhoneEntity> filtrar(PhoneFilter telefoneFilter, Pageable pageable);

}
