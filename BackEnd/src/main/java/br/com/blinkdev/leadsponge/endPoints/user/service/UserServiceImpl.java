package br.com.blinkdev.leadsponge.endPoints.user.service;

import br.com.blinkdev.leadsponge.endPoints.user.TO.UsuarioTO;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
import br.com.blinkdev.leadsponge.endPoints.user.modelAssembler.UserModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.user.repository.UserRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.storage.Disco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl extends ErroMessage implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Disco disco;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserModelAssembler userModelAssembler;

	@Autowired
	private PagedResourcesAssembler<UserEntity> assembler;

	@Override
	public UserModel getById(Long id) {
		log.info("CampanhaServiceImpl - getById");
		return userRepository.findById(id).map(userModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "a user"));
	}

	@Override
	public PagedModel<UserModel> searchWithFilter(UserFilter userFilter, Pageable pageable) {
		log.info("CampanhaServiceImpl - getCampanhaByFilter");
		return assembler.toModel(userRepository.searchWithFilter(userFilter, pageable), userModelAssembler);
	}

	@Override
	public UserEntity save(UserEntity usuario) {
		if (!usuario.getConfirmarPassword().equals(usuario.getPassword()))
			throw otherMensagemBadRequest("O campo de senha não corresponde com o confirmar senha.");
		else if (userRepository.findByUsername(usuario.getUsername()).isPresent())
			throw otherMensagemBadRequest("O nome de usuario já existe.");
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return userRepository.save(usuario);
	}

	@Override
	public UserEntity updatePatch(Long id, Map<Object, Object> fields) {
		log.info("CampanhaServiceImpl - update patch");
		UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "campanha"));
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(UserEntity.class, (String) key);
			assert field != null;
			field.setAccessible(true);
			ReflectionUtils.setField(field, userEntity, value);
		});
		return save(userEntity);
	}


	@Override
	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "a user"));
		usuarioSalva.setEnabled(enabled);
		userRepository.save(usuarioSalva);
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
	public UserEntity delete(Long id) {
		UserEntity usuarioSalvo = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "a user"));
		userRepository.deleteById(id);
		return usuarioSalvo;
	}

	@Override
	public UserEntity findByNome(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> notFould("o user"));
	}

	@Override
	public UserEntity atualizarUsuarioDTO(Long id, UsuarioTO usuario) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "o user"));
		usuarioSalva.getRoles().clear();
		usuarioSalva.getRoles().addAll(usuario.getRoles());
		BeanUtils.copyProperties(usuario, usuarioSalva, "id", "roles");
		return userRepository.save(usuarioSalva);
	}

	@Override
	public void removerImg(Long id) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "o user"));
		disco.remover(usuarioSalva.getUrlFoto());
		usuarioSalva.setFoto(null);
		usuarioSalva.setUrlFoto(null);
		userRepository.save(usuarioSalva);
	}

	@Override
	public void atualizarImg(Long id, String foto) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "o user"));
		usuarioSalva.setFoto(foto);
		usuarioSalva.setUrlFoto(disco.configurarUrlFoto(foto));
		userRepository.save(usuarioSalva);
	}

	// TODO ver a viabilidade
//	private UsuarioModel toModel(Usuario usuario){
//		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
//		usuarioModel.add(linkTo(methodOn(UsuarioEndPoint.class).list(new UsuarioFilter(), null)).withRel(IanaLinkRelations.COLLECTION));
//		return usuarioModel;
//	}
}