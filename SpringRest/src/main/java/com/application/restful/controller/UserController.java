package com.application.restful.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.restful.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users")
public class UserController {

	private static Map<String, User> userRepo = new HashMap<>();

	@RequestMapping("/check")
	public String status() {
		return "Working";
	}

	/* Adding Users */
	@PostMapping("/addUser")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		userRepo.put(user.getId(), user);
		System.out.println(user.getId());

		return new ResponseEntity<>("User is created", HttpStatus.CREATED);
	}

	/* Get Users by id */
	@GetMapping(value = "/user/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> findUserById(@PathVariable String id) {

		return new ResponseEntity<>(userRepo.get(id), HttpStatus.OK);

	}

	/* Gets all users */
	@GetMapping(value = "/allUsers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getUsers() {

		return new ResponseEntity<>(userRepo.values(), HttpStatus.OK);
	}

	/* Delete User */
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
		userRepo.remove(id);

		return new ResponseEntity<>("User is deleted successsfully", HttpStatus.OK);
	}

}
