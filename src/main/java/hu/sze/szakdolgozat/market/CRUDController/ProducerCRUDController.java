package hu.sze.szakdolgozat.market.CRUDController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.dto.FullUserResponse;
import hu.sze.szakdolgozat.market.entity.User;
import hu.sze.szakdolgozat.market.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class ProducerCRUDController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	

	@GetMapping("/producers")
	public List<FullUserResponse> costumers() {

		List<FullUserResponse> customerList = new ArrayList<>();
		List<User> tempUsers = userRepository.findByRole("producer");
		for (int i = 0; i < tempUsers.size(); i++) {
			User singleUser = tempUsers.get(i);
			FullUserResponse singleCustomer = new FullUserResponse();
			singleCustomer.setId(singleUser.getId());
			singleCustomer.setFirstname(singleUser.getFirstname());
			singleCustomer.setLastname(singleUser.getLastname());
			singleCustomer.setUsername(singleUser.getUsername());
			singleCustomer.setEmail(singleUser.getEmail());
			// singleCustomer.setImage(singleUser.getImage());
			singleCustomer.setPhonenumber(singleUser.getPhonenumber());
			customerList.add(singleCustomer);

		}
		return customerList;

	}

	@GetMapping("/producer/{id}")
	public FullUserResponse costumer(@PathVariable Integer id) {

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

	@PostMapping("/addProducer")
	public String addUserByadmin(@RequestBody User user) {

		return userService.addUserService(user);

	}

	@PutMapping("/editProducer/{id}")
	public String editCustomer(@RequestBody User user, @PathVariable("id") Integer id) {

		return userService.editUserService(user, id);
	}
}
