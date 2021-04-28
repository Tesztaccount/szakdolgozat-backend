package hu.sze.szakdolgozat.market.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.dto.UserResponse;
import hu.sze.szakdolgozat.market.entity.User;
import hu.sze.szakdolgozat.market.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@GetMapping("/auth/login")
	public UserResponse login(@RequestParam String user) {

		User tempUser = userRepository.getUserByUsername(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setId(tempUser.getId());
		userResponse.setRole(tempUser.getRole());
		return userResponse;

	}
	
	@PutMapping("/editCustomerPassword/")
	public String changePassword(@RequestBody User user){

		User singleUser = userRepository.findByEmail(user.getEmail());
		if(singleUser==null){
			return "helytelen";
		}
		if(singleUser.getUsername().equals(user.getUsername())){
			String encodedPw = passwordEncoder.encode(user.getPassword());
			singleUser.setPassword(encodedPw);
			userRepository.save(singleUser);
			return "ok";
		}
		return "helytelen";
	}

	@PostMapping("/registerCustomer")
	public String customerRegistration(@RequestBody User user) {

		return userService.addUserService(user);
		 
	}

}