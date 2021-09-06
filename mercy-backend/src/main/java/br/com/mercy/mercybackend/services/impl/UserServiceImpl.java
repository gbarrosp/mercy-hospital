package br.com.mercy.mercybackend.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercy.mercybackend.entities.UserEntity;
import br.com.mercy.mercybackend.repositories.UserRepository;
import br.com.mercy.mercybackend.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

}
