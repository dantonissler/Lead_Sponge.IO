package com.leadsponge.IO.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.leadsponge.IO.config.property.LeadSpongeApiProperty;
import com.leadsponge.IO.errorValidate.ResourceBadRequestException;

@Component
public class Disco {

	@Autowired
	private LeadSpongeApiProperty property;

	public Disco(LeadSpongeApiProperty property) {
		this.property = property;
	}

	public String salvarFoto(MultipartFile foto) {
		String nomeUnico = gerarNomeUnico(foto.getOriginalFilename());
		Path diretorioPath = Paths.get(property.getDisco().getRaiz(), property.getDisco().getDiretorioFotos());
		Path arquivoPath = diretorioPath.resolve(nomeUnico);

		try {
			Files.createDirectories(diretorioPath);
			foto.transferTo(arquivoPath.toFile());
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo. ", e);
		}
		return nomeUnico;
	}

	public String configurarUrlFoto(String objeto) {
		return property.getDisco().getDiretorioFotos() + objeto;
	}

	private String gerarNomeUnico(String originalFilename) {
		return UUID.randomUUID().toString() + "_" + originalFilename;
	}

	public void remover(String objeto) {
		File file = new File(property.getDisco().getRaiz() + objeto);
		try {
			file.delete();
		} catch (ResourceBadRequestException e) {
			throw new ResourceBadRequestException("Endereço do arquivo não é valido" + objeto);
		}
	}
}
