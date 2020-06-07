package com.leadsponge.IO.endPoints;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.Segmento;
import com.leadsponge.IO.models.view.View;
import com.leadsponge.IO.repository.SegmentoRepository;

@RestController
@RequestMapping("segmentos")
class SegmentoEndPoint {
	@Autowired
	SegmentoRepository segmentoRepository;
	
	public SegmentoEndPoint(SegmentoRepository segmentoRepository) {
		this.segmentoRepository = segmentoRepository;
	}
	
	@PostMapping
    public Segmento saveCustomer(@Valid @RequestBody Segmento customer) {
      return segmentoRepository.save(customer);
    }
 
    @PutMapping
    public Segmento updatedCustomer(@Valid @RequestBody Segmento updatedCustomer) {
        return segmentoRepository.findById(updatedCustomer.getId())
                .map(customer -> {
                    customer.setNome(updatedCustomer.getNome());
                    return segmentoRepository.save(customer);
                }).orElseThrow(() -> new RuntimeException ("Customer not found with id " + updatedCustomer.getId()));
    }
    
    @GetMapping
    @JsonView(View.Segmento.class)
    public List<Segmento> getAll(){
      return segmentoRepository.findAll();
    }

}
