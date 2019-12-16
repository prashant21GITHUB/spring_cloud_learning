package com.pyb.app.controller;

import com.pyb.app.models.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pyb.app.models.User;

@RestController
@RequestMapping("users")
public class UserController {

	@GetMapping
	public String getUsers(@RequestParam(value="page", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "10") int l) {
		return "Get user Success: page: "+page+" , limit: "+l;
	}
	
	
	@GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		User user = new User();
		user.setFirstName(userId);
		user.setLastName(userId);

		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(consumes = {
	        MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
	        MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
	public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
	    return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PutMapping
	public String putUser() {
		return "Update user Success";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "Delete user Success";
	}
	
}
