package hu.sze.szakdolgozat.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.dto.UserResponse;
import hu.sze.szakdolgozat.market.entity.User;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/auth/login")
	public UserResponse login(@RequestParam String user) {

		User tempUser = userRepository.getUserByUsername(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setId(tempUser.getId());

		return userResponse;

	}

	@PostMapping("/addCustomer")
	public String addUserByAdmin(@RequestBody User user) {

		if (userRepository.getUserByUsername(user.getUsername()) != null) {
			
			return "user already exists";
			
		} else if(userRepository.findByEmail(user.getEmail()) != null){
			 
			return "email already exists";

		}
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "user added successfully...";
	}

	@GetMapping("/process")
	public String process() {
		return "processing..";
	}

}