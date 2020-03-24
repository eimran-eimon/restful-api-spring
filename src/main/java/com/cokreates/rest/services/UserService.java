package com.cokreates.rest.services;

import com.cokreates.rest.common.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	UserDTO createUser(UserDTO userDTO);
}
