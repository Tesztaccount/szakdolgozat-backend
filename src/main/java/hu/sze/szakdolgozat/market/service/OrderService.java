package hu.sze.szakdolgozat.market.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;

@Service
public class OrderService {
    
    private OrderRepository orderRepository;

    private OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository){
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    public ResponseEntity<Object> addOrder(Order order){

        Order newOrder = new Order();
        
        newOrder.setUser(order.getUser());
        newOrder.setTotalPrice(order.getTotalPrice());
        newOrder.setStatus(order.getStatus());
        
        newOrder.setOrderDetails(order.getOrderDetails());

        Order savedOrder = orderRepository.save(newOrder);
        if(orderRepository.findById(savedOrder.getId()).isPresent()){
            return ResponseEntity.ok().body("Successfull order creation");
        }else
            return ResponseEntity.unprocessableEntity().body("Failed to create order");
    }


}
