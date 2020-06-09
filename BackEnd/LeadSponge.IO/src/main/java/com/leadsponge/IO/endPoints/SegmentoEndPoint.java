package com.leadsponge.IO.endPoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.SegmentoRepository;

@RestController
@RequestMapping("segmentos")
class SegmentoEndPoint {

	@Autowired
	SegmentoRepository segmentoRepository;
	
	public SegmentoEndPoint(SegmentoRepository segmentoRepository) {
		this.segmentoRepository = segmentoRepository;
	}

    @GetMapping
    @JsonView(View.Segmento.class)
    public List<Segmento> pesquisar(){
      return segmentoRepository.findAll();
    }

}
