package hu.sze.szakdolgozat.market.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import hu.sze.szakdolgozat.market.entity.Order;

@CrossOrigin("http://localhost:4200")
public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findAllByOrderDateBetween(
        LocalDate orderDate,
        LocalDate orderDateEnd);

}
