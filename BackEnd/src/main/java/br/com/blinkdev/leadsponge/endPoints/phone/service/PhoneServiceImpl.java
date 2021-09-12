package br.com.blinkdev.leadsponge.endPoints.phone.service;

import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.filter.PhoneFilter;
import br.com.blinkdev.leadsponge.endPoints.phone.repository.PhoneRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl extends ErroMessage implements PhoneService {

    @Autowired
    private PhoneRepository repository;

    @Override
    public Page<PhoneEntity> filtrar(PhoneFilter telefoneFilter, Pageable pageable) {
        return repository.filtrar(telefoneFilter, pageable);
    }

    @Override
    public PhoneEntity salvar(PhoneEntity telefone) {
        return repository.save(telefone);
    }

    @Override
    public PhoneEntity atualizar(Long id, PhoneEntity telefone) {
        PhoneEntity telefoneSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a telefone"));
        BeanUtils.copyProperties(telefone, telefoneSalvo, "id");
        return repository.save(telefoneSalvo);
    }

    @Override
    public PhoneEntity deletar(Long id) {
        PhoneEntity telefoneSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a telefone"));
        repository.deleteById(id);
        return telefoneSalvo;
    }

    @Override
    public PhoneEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a telefone"));
    }
}
