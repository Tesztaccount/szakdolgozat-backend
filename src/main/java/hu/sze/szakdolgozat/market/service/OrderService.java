package hu.sze.szakdolgozat.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public ResponseEntity<Object> addOrder(Order order) {
        Order newOrder = new Order();
        newOrder.setUser(order.getUser());
        newOrder.setTotalPrice(order.getTotalPrice());
        newOrder.setStatus(order.getStatus());

        newOrder.setOrderDetails(order.getOrderDetails());

        Order savedOrder = orderRepository.save(newOrder);
        if (orderRepository.findById(savedOrder.getId()).isPresent()) {
            return ResponseEntity.ok().body(savedOrder);
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to create order");
    }

    public void orderRefresh(Long orderId) {
        Optional<Order> tempOrder = orderRepository.findById(orderId);
        if (tempOrder.isPresent()) {
            Order order = tempOrder.get();
            orderRepository.save(refreshSingleOrder(order));
        }
    }

    public void refreshAll() {
        List<Order> tempOrders = orderRepository.findAll();
        for (int i = 0; i < tempOrders.size(); i++) {
            Order order = tempOrders.get(i);
            orderRepository.save(refreshSingleOrder(order));
        }
    }

    private Order refreshSingleOrder(Order order) {
        Integer newPrice = 0;
        Boolean isDone = true;
        List<OrderDetail> tempDetails = order.getOrderDetails();
        for (int i = 0; i < tempDetails.size(); i++) {

            OrderDetail temp = tempDetails.get(i);
            newPrice = temp.getUnitprice() * temp.getQuantity() + newPrice;
            if (temp.getStatus().equals("Folyamatban")) {
                isDone = false;
            }
        }
        if(!order.getStatus().equals("Törölve")){
            order.setStatus(orderStatus(isDone));
        }
        order.setTotalPrice(newPrice);
        return order;
    }

    private String orderStatus(Boolean isDone) {
        if (Boolean.TRUE.equals(isDone)) {
            return "Elkészült";
        } else {
            return "Folyamatban";
        }
    }
}
