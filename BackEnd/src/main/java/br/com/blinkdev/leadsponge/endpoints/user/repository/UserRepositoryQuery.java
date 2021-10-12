package br.com.blinkdev.leadsponge.endpoints.user.repository;

import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endpoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endpoints.user.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryQuery {

    Page<UserEntity> searchWithFilter(UserFilter usuarioFilter, Pageable pageable);

    Page<UserModel> resumir(UserFilter usuarioFilter, Pageable pageable);
}
