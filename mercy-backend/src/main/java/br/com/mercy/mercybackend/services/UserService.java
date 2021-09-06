package br.com.mercy.mercybackend.services;

import br.com.mercy.mercybackend.entities.UserEntity;

public interface UserService {

    Boolean existsByUsername(String username);

    UserEntity saveUser(UserEntity user);
    
}
