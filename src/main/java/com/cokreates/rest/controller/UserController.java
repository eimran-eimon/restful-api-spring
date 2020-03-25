package com.cokreates.rest.controller;


import com.cokreates.rest.common.UserDTO;
import com.cokreates.rest.model.request.UserDetailsRequestModel;
import com.cokreates.rest.model.response.UserRest;
import com.cokreates.rest.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

		UserRest userRest = new UserRest();

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		UserDTO createdUser = userService.createUser(userDTO);
		BeanUtils.copyProperties(createdUser, userRest);

		return userRest;
	}

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id){
		UserRest userRest = new UserRest();
		UserDTO userDTO = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDTO,userRest);
		return userRest;
	}


}
