package hu.sze.szakdolgozat.market.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;
import hu.sze.szakdolgozat.market.service.OrderService;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

   
    @PostMapping("/postOrder")
    public ResponseEntity<Object> createOrder(@RequestBody Order order){
        
        return orderService.addOrder(order);

    }

    @GetMapping("/order/{id}")
	public String delete(@PathVariable long id) {

        Optional<Order> tempOrder = orderRepository.findById(id);
        Order order = tempOrder.get();
        order.setStatus("Törölve");
        orderRepository.save(order);
        return "deleted";
        

	}

}
