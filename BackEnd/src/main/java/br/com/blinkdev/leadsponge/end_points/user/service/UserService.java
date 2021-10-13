package br.com.blinkdev.leadsponge.end_points.user.service;

import br.com.blinkdev.leadsponge.end_points.user.TO.UsuarioTO;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.end_points.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.end_points.user.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    UserModel getById(Long id);

    PagedModel<UserModel> searchWithFilter(UserFilter usuarioFilter, Pageable pageable);

    UserModel save(UserEntity usuario);

    UserModel patch(Long id, Map<Object, Object> fields);

    UserModel delete(Long id);

    void atualizarPropriedadeEnabled(Long id, Boolean enabled);

    String findLoggedInLogin();

    UserEntity atualizarUsuarioDTO(Long id, UsuarioTO usuario);

    void removerImg(Long id);

    void atualizarImg(Long id, String foto);
}
