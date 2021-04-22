package hu.sze.szakdolgozat.market.CRUDController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.dto.FullUserResponse;
import hu.sze.szakdolgozat.market.dto.UserResponse;
import hu.sze.szakdolgozat.market.entity.User;
import hu.sze.szakdolgozat.market.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class AdminCRUDController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
 
	@GetMapping("/login")
	public UserResponse login(@RequestParam String user) {

		User tempUser = userRepository.getUserByUsername(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setId(tempUser.getId());
		userResponse.setRole(tempUser.getRole());
		return userResponse;

	}

	@GetMapping("/admins")
	public List<FullUserResponse> admins() {

		List<FullUserResponse> customerList = new ArrayList<>();
		List<User> tempUsers = userRepository.findByRole("admin");
		for (int i = 0; i < tempUsers.size(); i++) {
			User singleUser = tempUsers.get(i);
			FullUserResponse singleCustomer = new FullUserResponse();
			singleCustomer.setId(singleUser.getId());
			singleCustomer.setFirstname(singleUser.getFirstname());
			singleCustomer.setLastname(singleUser.getLastname());
			singleCustomer.setUsername(singleUser.getUsername());
			singleCustomer.setEmail(singleUser.getEmail());
			singleCustomer.setPhonenumber(singleUser.getPhonenumber());
			customerList.add(singleCustomer);

		}
		return customerList;

	}

	@GetMapping("/admin/{id}")
	public FullUserResponse admin(@PathVariable Integer id) {

		FullUserResponse producerResponse = new FullUserResponse();
		Optional<User> singleCustomer = userRepository.findById(id);
		User tempCustomer = singleCustomer.get();

		producerResponse.setId(tempCustomer.getId());
		producerResponse.setFirstname(tempCustomer.getFirstname());
		producerResponse.setLastname(tempCustomer.getLastname());
		producerResponse.setUsername(tempCustomer.getUsername());
		producerResponse.setEmail(tempCustomer.getEmail());
		producerResponse.setPhonenumber(tempCustomer.getPhonenumber());

		return producerResponse;

	}

	@PostMapping("/addAdmin")
	public String addAdminByadmin(@RequestBody User user) {

		return userService.addUserService(user);

	}

	@PutMapping("/editAdmin/{id}")
	public String editAdmin(@RequestBody User user, @PathVariable("id") Integer id) {

		return userService.editUserService(user, id);
	
	}

	@DeleteMapping("/deleteAdmin/{id}")
	public String delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "deleted";
	}
}
