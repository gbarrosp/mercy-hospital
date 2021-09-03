package br.com.mercy.mercybackend.repositories;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mercy.mercybackend.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Id> {
    
}
