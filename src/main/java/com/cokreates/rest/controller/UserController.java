package com.cokreates.rest.controller;


import com.cokreates.rest.common.AddressDTO;
import com.cokreates.rest.common.RequestOperationName;
import com.cokreates.rest.common.RequestOperationStatus;
import com.cokreates.rest.common.UserDTO;
import com.cokreates.rest.common.exception.UserServiceException;
import com.cokreates.rest.model.request.UserDetailsRequestModel;
import com.cokreates.rest.model.response.AddressesRest;
import com.cokreates.rest.model.response.OperationStatusModel;
import com.cokreates.rest.model.response.UserRest;
import com.cokreates.rest.model.response.exception.ErrorMessages;
import com.cokreates.rest.services.AddressService;
import com.cokreates.rest.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	UserService userService;
	AddressService addressService;

	public UserController(UserService userService, AddressService addressService) {
		this.userService = userService;
		this.addressService = addressService;
	}

	@PostMapping(
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);

		UserDTO createdUser = userService.createUser(userDTO);
		UserRest returnValue = modelMapper.map(createdUser, UserRest.class);


		return returnValue;
	}

	@GetMapping(path = "/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		UserDTO userDTO = userService.getUserByUserId(id);
		UserRest userRest = new ModelMapper().map(userDTO, UserRest.class);
		return userRest;
	}


	// update firstName and lastName only.
	@PutMapping(
			path = "/{id}",
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			}
	)

	public UserRest updateUserDetails(@PathVariable String id,
	                                  @RequestBody UserDetailsRequestModel userDetails) {


		UserRest userRest = new UserRest();

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);
		//TODO: use model mapper
		UserDTO updateUser = userService.updateUser(userDTO, id);
		BeanUtils.copyProperties(updateUser, userRest);

		return userRest;
	}

	@DeleteMapping(
			path = "/{id}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			}
	)

	public OperationStatusModel deleteUser(@PathVariable String id) {

		OperationStatusModel statusModel = new OperationStatusModel();
		userService.deleteUser(id);
		statusModel.setOperationName(RequestOperationName.DELETE.name());
		statusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return statusModel;
	}

	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
	                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {

		List<UserRest> usersList = new ArrayList<>();

		List<UserDTO> users = userService.getUsers(page, limit);

		for (UserDTO userDTO : users) {
			usersList.add(new ModelMapper().map(userDTO, UserRest.class));
		}

		return usersList;
	}

	@GetMapping(path = "/{id}/addresses",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public List<AddressesRest> getUserAddresses(@PathVariable String id) {

		List<AddressesRest> returnValue = new ArrayList<>();

		List<AddressDTO> addressesDTO = addressService.getAddresses(id);
		if (addressesDTO != null && !addressesDTO.isEmpty()) {
			Type listType = new TypeToken<List<AddressesRest>>() {
			}.getType();
			ModelMapper modelMapper = new ModelMapper();
			returnValue = modelMapper.map(addressesDTO, listType);
		}

		return returnValue;
	}


}
