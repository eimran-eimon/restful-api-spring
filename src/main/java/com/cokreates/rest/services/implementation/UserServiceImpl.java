package com.cokreates.rest.services.implementation;

import com.cokreates.rest.common.UserDTO;
import com.cokreates.rest.common.Utils;
import com.cokreates.rest.model.entity.UserEntity;
import com.cokreates.rest.repository.UserRepository;
import com.cokreates.rest.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	BCryptPasswordEncoder bCryptPasswordEncoder;
	UserRepository userRepository;
	Utils utils;


	public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.utils = utils;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO)
	{
		UserEntity isExists = userRepository.findByEmail(userDTO.getEmail());
		if(isExists!=null){
			throw new RuntimeException("Record already exists!");
		}
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		userEntity.setUserId(utils.generateUserId(30));
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}
}
