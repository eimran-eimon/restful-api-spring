package com.cokreates.rest.services.implementation;

import com.cokreates.rest.common.UserDTO;
import com.cokreates.rest.common.Utils;
import com.cokreates.rest.model.entity.UserEntity;
import com.cokreates.rest.repository.UserRepository;
import com.cokreates.rest.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	Utils utils;

	public UserServiceImpl(UserRepository userRepository, Utils utils) {
		this.userRepository = userRepository;
		this.utils = utils;
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
		userEntity.setEncryptedPassword("encryt_password");
		userEntity.setUserId(utils.generateUserId(30));
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}
}
