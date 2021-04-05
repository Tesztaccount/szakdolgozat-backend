package hu.sze.szakdolgozat.market.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import hu.sze.szakdolgozat.market.entity.User;

@CrossOrigin("http://localhost:4200")
public interface UserRepository extends JpaRepository<User, Integer>{

    User getUserByUsername(String username);
    User findByEmail(String email);

    List<User> findByRole(String role);
    
}
