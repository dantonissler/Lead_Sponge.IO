package com.leadsponge.IO.endPoints.crudEndpoints;

import com.leadsponge.IO.errorValidate.ResourceNotFoundException;


public abstract class CrudController{
	
	protected ResourceNotFoundException notFouldId(Long id, String nome) {
		return new ResourceNotFoundException("Não foi possivel encontrar "+nome+" com o id: " + id);
	}
}
