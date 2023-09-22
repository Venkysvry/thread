package com.hr.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.User;
import com.hr.model.UserModel;
import com.hr.services.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/sc")
public class ServiceController {
	@Autowired
	UserService userservice;

	@GetMapping("viewAllAccounts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllAccounts() {
		List<User> userlist = userservice.getAllAccounts();
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}

	@PutMapping("/changepassword/{id}/{name}")
	@PreAuthorize("hasRole('FARMER') or hasRole('DEALER') or hasRole('ADMIN')")
	public ResponseEntity<String> changePassword(@RequestBody String id, String name) {
		String s = userservice.forgotpasswrod(id, name);
		return new ResponseEntity<>(s, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('FARMER') or hasRole('DEALER') or hasRole('ADMIN')")
	public ResponseEntity<String> updateProfile(@PathVariable String id, @RequestBody UserModel user) {
		String s = userservice.update(id, user);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER') or hasRole('FARMER')")
	public ResponseEntity<User> deleteAccount(@PathVariable String id) {

	userservice.delete(id);
		User u = userservice.getByid(id);
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<User> getAccount(@PathVariable String id) {
		User u = userservice.getByid(id);
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
}
