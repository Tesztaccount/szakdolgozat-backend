package hu.sze.szakdolgozat.market.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import hu.sze.szakdolgozat.market.entity.OrderDetail;

@CrossOrigin("http://localhost:4200")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
