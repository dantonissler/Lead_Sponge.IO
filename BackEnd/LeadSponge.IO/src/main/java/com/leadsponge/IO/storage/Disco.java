package com.leadsponge.IO.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.leadsponge.IO.config.property.LeadSpongeApiProperty;

@Component
public class Disco {

	@Autowired
	private LeadSpongeApiProperty property;
	
	public Disco(LeadSpongeApiProperty property) {
		super();
		this.property = property;
	}

	public void salvarImagem(MultipartFile foto) {
		this.salvar(property.getDisco().getDiretorioFotos(), foto);
	}

	public void salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(property.getDisco().getRaiz(), diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}
	}
}
