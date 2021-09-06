package br.com.mercy.mercybackend.services;

import java.util.Optional;

import br.com.mercy.mercybackend.entities.UserEntity;

public interface UserService {

    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    UserEntity saveUser(UserEntity user);
    
}
