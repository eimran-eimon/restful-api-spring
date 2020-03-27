package com.cokreates.rest.services.implementation;

import com.cokreates.rest.common.AddressDTO;
import com.cokreates.rest.model.entity.AddressEntity;
import com.cokreates.rest.model.entity.UserEntity;
import com.cokreates.rest.model.response.AddressesRest;
import com.cokreates.rest.repository.AddressRepository;
import com.cokreates.rest.repository.UserRepository;
import com.cokreates.rest.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

	UserRepository userRepository;
	AddressRepository addressRepository;

	public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	public List<AddressDTO> getAddresses(String id) {
		List<AddressDTO> returnValue = new ArrayList<>();

		UserEntity userEntity = userRepository.findByUserId(id);

		List<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);

		for(AddressEntity addressEntity : addresses){
			returnValue.add(new ModelMapper().map(addressEntity, AddressDTO.class));
		}

		return returnValue;
	}
}
