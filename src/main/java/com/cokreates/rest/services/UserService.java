package com.cokreates.rest.services;

import com.cokreates.rest.common.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	UserDTO createUser(UserDTO userDTO);
	UserDTO getUser(String email);
	UserDTO getUserByUserId(String id);
	UserDTO updateUser(UserDTO userDTO, String id);
}
