package br.com.mercy.mercybackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercy.mercybackend.entities.UserEntity;
import br.com.mercy.mercybackend.repositories.UserRepository;
import br.com.mercy.mercybackend.security.UserPrinciple;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("Username not found : " + username));

		return UserPrinciple.build(user);
	}

}
