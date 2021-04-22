package hu.sze.szakdolgozat.market.CRUDController;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.dto.FullUserResponse;
import hu.sze.szakdolgozat.market.dto.UserResponse;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;
import hu.sze.szakdolgozat.market.entity.ProductReview;
import hu.sze.szakdolgozat.market.entity.User;
import hu.sze.szakdolgozat.market.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class CustomerCRUDController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping("/customers")
	public List<FullUserResponse> costumers() {

		List<FullUserResponse> customerList = new ArrayList<>();
		List<User> tempUsers = userRepository.findByRole("customer");
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

	@GetMapping("/customer/{id}")
	public FullUserResponse costumer(@PathVariable Integer id) {

		FullUserResponse customerResponse = new FullUserResponse();
		Optional<User> singleCustomer = userRepository.findById(id);
		User tempCustomer = singleCustomer.get();

		customerResponse.setId(tempCustomer.getId());
		customerResponse.setFirstname(tempCustomer.getFirstname());
		customerResponse.setLastname(tempCustomer.getLastname());
		customerResponse.setUsername(tempCustomer.getUsername());
		customerResponse.setEmail(tempCustomer.getEmail());
		customerResponse.setPhonenumber(tempCustomer.getPhonenumber());

		return customerResponse;

	}

	@PostMapping("/addCustomer")
	public String addUserByadmin(@RequestBody User user) {

		
		return userService.addUserService(user);

	}

	@PutMapping("/editCustomer/{id}")
	public String editCustomer(@RequestBody User user, @PathVariable("id") Integer id) {

		return userService.editUserService(user, id);
		
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public String delete(@PathVariable Integer id) {

        userRepository.deleteById(id);
        return "deleted";
	}
}
