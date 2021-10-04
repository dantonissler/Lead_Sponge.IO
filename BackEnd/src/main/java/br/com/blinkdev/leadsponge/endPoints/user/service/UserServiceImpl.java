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
		log.info("UserServiceImpl - getById");
		return userRepository.findById(id).map(userModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[user]"));
	}

	@Override
	public PagedModel<UserModel> searchWithFilter(UserFilter userFilter, Pageable pageable) {
		log.info("UserServiceImpl - searchWithFilter");
		return assembler.toModel(userRepository.searchWithFilter(userFilter, pageable), userModelAssembler);
	}

	@Override
	public UserModel save(UserEntity usuario) {
		log.info("UserServiceImpl - save");
		if (!usuario.getConfirmarPassword().equals(usuario.getPassword()))
			throw otherMensagemBadRequest("O campo de senha não corresponde com o confirmar senha.");
		else if (userRepository.findByUsername(usuario.getUsername()).isPresent())
			throw otherMensagemBadRequest("O nome de usuario já existe.");
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return userModelAssembler.toModel(userRepository.save(usuario));
	}

	@Override
	public UserModel patch(Long id, Map<Object, Object> fields) {
		log.info("UserServiceImpl - patch");
		UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "[user]"));
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(UserEntity.class, (String) key);
			assert field != null;
			field.setAccessible(true);
			ReflectionUtils.setField(field, userEntity, value);
		});
		return save(userEntity);
	}

	@Override
	public UserModel delete(Long id) {
		log.info("UserServiceImpl - delete");
		UserEntity usuarioSalvo = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "[user]"));
		userRepository.deleteById(id);
		return userModelAssembler.toModel(usuarioSalvo);
	}


	@Override
	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "[user]"));
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
	public UserEntity atualizarUsuarioDTO(Long id, UsuarioTO usuario) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "[user]"));
		usuarioSalva.getRoles().clear();
		usuarioSalva.getRoles().addAll(usuario.getRoles());
		BeanUtils.copyProperties(usuario, usuarioSalva, "id", "roles");
		return userRepository.save(usuarioSalva);
	}

	@Override
	public void removerImg(Long id) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "[user]"));
		disco.remover(usuarioSalva.getUrlFoto());
		usuarioSalva.setFoto(null);
		usuarioSalva.setUrlFoto(null);
		userRepository.save(usuarioSalva);
	}

	@Override
	public void atualizarImg(Long id, String foto) {
		UserEntity usuarioSalva = userRepository.findById(id).orElseThrow(() -> notFouldId(id, "[user]"));
		usuarioSalva.setFoto(foto);
		usuarioSalva.setUrlFoto(disco.configurarUrlFoto(foto));
		userRepository.save(usuarioSalva);
	}
}