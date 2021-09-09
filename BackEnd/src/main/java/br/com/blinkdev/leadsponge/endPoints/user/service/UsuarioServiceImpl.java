package br.com.blinkdev.leadsponge.endPoints.user.service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
import br.com.blinkdev.leadsponge.endPoints.user.TO.UsuarioTO;
import br.com.blinkdev.leadsponge.endPoints.user.repository.UsuarioRepository;
import br.com.blinkdev.leadsponge.storage.Disco;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UsuarioServiceImpl extends ErroMessage implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private Disco disco;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Autowired
//	private ModelMapper modelMapper;

	@Override
	public UserEntity atualizarUsuarioDTO(Long id, UsuarioTO usuario) {
		UserEntity usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		usuarioSalva.getRoles().clear();
		usuarioSalva.getRoles().addAll(usuario.getRoles());
		BeanUtils.copyProperties(usuario, usuarioSalva, "id", "roles");
		return repository.save(usuarioSalva);
	}

	@Override
	public void removerImg(Long id) {
		UserEntity usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		disco.remover(usuarioSalva.getUrlFoto());
		usuarioSalva.setFoto(null);
		usuarioSalva.setUrlFoto(null);
		repository.save(usuarioSalva);
	}

	@Override
	public void atualizarImg(Long id, String foto) {
		UserEntity usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		usuarioSalva.setFoto(foto);
		usuarioSalva.setUrlFoto(disco.configurarUrlFoto(foto));
		repository.save(usuarioSalva);
	}

	@Override
	public UserEntity salvar(UserEntity usuario) {
		if (!usuario.getConfirmarPassword().equals(usuario.getPassword()))
			throw otherMensagemBadRequest("O campo de senha não corresponde com o confirmar senha.");
		else if (repository.findByUsername(usuario.getUsername()).isPresent())
			throw otherMensagemBadRequest("O nome de usuario já existe.");
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return repository.save(usuario);
	}

	@Override
	public UserEntity atualizar(Long id, UserEntity usuario) {
		UserEntity usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioSalva.getRoles().clear();
		usuarioSalva.getRoles().addAll(usuario.getRoles());
		usuarioSalva.setRoles(new HashSet<>(usuarioSalva.getRoles()));
		if (usuario.getFoto().isBlank() && usuarioSalva.getFoto().isBlank()) {
			disco.remover(usuarioSalva.getUrlFoto());
		} else if (usuario.getFoto().isBlank() && !usuario.getFoto().equals(usuarioSalva.getFoto())) {
			disco.remover(usuario.getUrlFoto());
		}
		BeanUtils.copyProperties(usuario, usuarioSalva, "id", "roles");
		return repository.save(usuarioSalva);
	}

	@Override
	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		UserEntity usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		usuarioSalva.setEnabled(enabled);
		repository.save(usuarioSalva);
	}

	@Override
	public String findLoggedInLogin() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	@Override
	public Page<UserEntity> filtrar(UserFilter usuarioFilter, Pageable pageable) {
		return repository.filtrar(usuarioFilter, pageable);
	}

	@Override
	public UserEntity deletar(Long id) {
		UserEntity usuarioSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		repository.deleteById(id);
		return usuarioSalvo;
	}

	@Override
	public UserModel detalhar(Long id) {
		return new UserModel();//repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
	}

	@Override
	public Page<UserModel> list(UserFilter usuarioFilter, Pageable pageable) {
		return repository.resumir(usuarioFilter, pageable);
	}

	@Override
	public UserEntity findByNome(String username) {
		return repository.findByUsername(username).orElseThrow(() -> notFould("o usuario"));
	}

	// TODO ver a viabilidade
//	private UsuarioModel toModel(Usuario usuario){
//		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
//		usuarioModel.add(linkTo(methodOn(UsuarioEndPoint.class).list(new UsuarioFilter(), null)).withRel(IanaLinkRelations.COLLECTION));
//		return usuarioModel;
//	}
}