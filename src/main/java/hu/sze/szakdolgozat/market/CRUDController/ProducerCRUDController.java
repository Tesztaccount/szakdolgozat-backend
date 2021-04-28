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

		List<FullUserResponse> producerList = new ArrayList<>();
		List<User> tempUsers = userRepository.findByRole("producer");
		for (int i = 0; i < tempUsers.size(); i++) {
			User singleUser = tempUsers.get(i);
			FullUserResponse singleProducer = new FullUserResponse();
			singleProducer.setId(singleUser.getId());
			singleProducer.setFirstname(singleUser.getFirstname());
			singleProducer.setLastname(singleUser.getLastname());
			singleProducer.setUsername(singleUser.getUsername());
			singleProducer.setEmail(singleUser.getEmail());
			singleProducer.setImage(singleUser.getImage());
			singleProducer.setPhonenumber(singleUser.getPhonenumber());
			producerList.add(singleProducer);

		}
		return producerList;

	}

	@GetMapping("/producer/{id}")
	public FullUserResponse costumer(@PathVariable Integer id) {

		FullUserResponse producerResponse = new FullUserResponse();
		Optional<User> singleProducer = userRepository.findById(id);
		User tempProducer = singleProducer.get();

		producerResponse.setId(tempProducer.getId());
		producerResponse.setFirstname(tempProducer.getFirstname());
		producerResponse.setLastname(tempProducer.getLastname());
		producerResponse.setUsername(tempProducer.getUsername());
		producerResponse.setEmail(tempProducer.getEmail());
		producerResponse.setPhonenumber(tempProducer.getPhonenumber());
		producerResponse.setImage(tempProducer.getImage());

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
