package br.com.blinkdev.leadsponge.endPoints.user.service;

import br.com.blinkdev.leadsponge.endPoints.user.TO.UsuarioTO;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    UserModel getById(Long id);

    PagedModel<UserModel> searchWithFilter(UserFilter usuarioFilter, Pageable pageable);

    UserEntity save(UserEntity usuario);

    UserEntity updatePatch(Long id, Map<Object, Object> fields);

    void atualizarPropriedadeEnabled(Long id, Boolean enabled);

    String findLoggedInLogin();

    UserEntity delete(Long id);

    UserEntity findByNome(String username);

    UserEntity atualizarUsuarioDTO(Long id, UsuarioTO usuario);

    void removerImg(Long id);

    void atualizarImg(Long id, String foto);
}
