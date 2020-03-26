package com.cokreates.rest.controller;


import com.cokreates.rest.common.RequestOperationName;
import com.cokreates.rest.common.RequestOperationStatus;
import com.cokreates.rest.common.UserDTO;
import com.cokreates.rest.common.exception.UserServiceException;
import com.cokreates.rest.model.request.UserDetailsRequestModel;
import com.cokreates.rest.model.response.OperationStatusModel;
import com.cokreates.rest.model.response.UserRest;
import com.cokreates.rest.model.response.exception.ErrorMessages;
import com.cokreates.rest.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
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

		UserRest userRest = new UserRest();

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		UserDTO createdUser = userService.createUser(userDTO);
		BeanUtils.copyProperties(createdUser, userRest);

		return userRest;
	}

	@GetMapping(path = "/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		UserRest userRest = new UserRest();
		UserDTO userDTO = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDTO, userRest);
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


}
