package hu.sze.szakdolgozat.market.controller;

import java.io.Console;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/postOrder")
    public ResponseEntity<Object> createOrder(@RequestBody Order order){
        
        return orderService.addOrder(order);

    }

    @GetMapping("/order/{id}")
	public String delete(@PathVariable long id) {

	    Order tempOrder = orderRepository.findById(id).get();
        List<OrderDetail> tempDetail = tempOrder.getOrderDetails();

        orderDetailRepository.deleteAll(tempDetail);
        orderRepository.delete(tempOrder);
        
        return "order deleted";
	}

}