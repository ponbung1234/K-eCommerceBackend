package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users optionalUsers = usersRepository.findByName(username);

		//optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		//return optionalUsers.map(CustomUserDetails::new).get();
		return new CustomUserDetails(optionalUsers);
	}

	
}
