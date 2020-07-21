//package com.leadsponge.IO.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.leadsponge.IO.errorValidate.FileStorageException;
//import com.leadsponge.IO.errorValidate.MyFileNotFoundException;
//import com.leadsponge.IO.models.imagens.Imagens;
//import com.leadsponge.IO.repository.ImagensRepository;
//import com.leadsponge.IO.storage.Disco;
//
//@Service
//public class ImagensService {
//
//	@Autowired
//	private ImagensRepository imagensRepository;
//
//	@Autowired
//	private Disco disco;
//
//	public Imagens salvar(MultipartFile imagem) {
//		String nomeImagem = StringUtils.cleanPath(imagem.getOriginalFilename());
//
//		try {
//			// Verificar se o nome tem caracteres invalidos
//			if (nomeImagem.contains("..")) {
//				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + nomeImagem);
//			}
//			Imagens objetoImagem = new Imagens(nomeImagem, imagem.getContentType());
//			disco.salvarImagem(imagem);
//			return imagensRepository.save(objetoImagem);
//		} catch (Exception ex) {
//			throw new FileStorageException("Could not store file " + nomeImagem + ". Please try again!", ex);
//		}
//	}
//	
//    public Imagens getFile(Long fileId) {
//        return imagensRepository.findById(fileId)
//                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
//}
