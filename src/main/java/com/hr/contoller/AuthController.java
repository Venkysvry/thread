package com.hr.contoller;
 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.ERole;
import com.hr.model.JwtResponse;
import com.hr.model.LoginRequest;
import com.hr.model.MessageResponse;
import com.hr.model.Role;
import com.hr.model.SignupRequest;
import com.hr.model.User;
import com.hr.repositories.RoleRepository;
import com.hr.repositories.UserRepository;
import com.hr.services.SequenceGeneratorService;
import com.hr.services.UserDetailsImpl;
import com.hr.util.JwtUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	SequenceGeneratorService generatorService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(),
				userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUserId(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber(),
				signUpRequest.getLocationName(), signUpRequest.getCountry(), signUpRequest.getState(),
				signUpRequest.getDistrict(), signUpRequest.getMandal(), signUpRequest.getPincode());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_FARMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}

		strRoles.forEach(role -> {
			switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
			case "dealer":
				Role dealerRole = roleRepository.findByName(ERole.ROLE_DEALER)
						.orElseThrow(() -> new RuntimeException("Error:Role is not found"));

				roles.add(dealerRole);
				break;

			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_FARMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);

			}
		});

		user.setRoles(roles);
		user.setUserId(generatorService.generateSequence(User.SEQUENCE_NAME));

		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
	}
	
	
	
//	@PutMapping("/forgotpassword")
//	public ResponseEntity<?> authenticateUser(@RequestBody UserDto userdto) {
//
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(),
//				userDetails.getEmail(), roles));
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
