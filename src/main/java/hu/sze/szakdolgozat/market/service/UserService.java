package hu.sze.szakdolgozat.market.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public String addUserService(User user) {

		if (userRepository.getUserByUsername(user.getUsername()) != null) {

			return "user already exists";

		} else if (userRepository.findByEmail(user.getEmail()) != null) {

			return "email already exists";

		}
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "user added successfully...";

	}

	public String editUserService(User user, Integer id) {

		Optional<User> singleUser = userRepository.findById(id);

		User tempUser = singleUser.get();
		tempUser.setFirstname(user.getFirstname());
		tempUser.setLastname(user.getLastname());
		tempUser.setUsername(user.getUsername());
		tempUser.setEmail(user.getEmail());
		tempUser.setPhonenumber(user.getPhonenumber());
		tempUser.setImage(user.getImage());

		if (StringUtils.isNotBlank(user.getPassword())) {

			String encodedPw = passwordEncoder.encode(user.getPassword());
			tempUser.setPassword(encodedPw);

		}

		userRepository.save(tempUser);

		return "ok";
	}

}
