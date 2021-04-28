package hu.sze.szakdolgozat.market.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dto.OrderResponse;
import hu.sze.szakdolgozat.market.entity.Order;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class StatController {
    
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/stats/{sDate}/{eDate}")
	public List<OrderResponse> stats(@PathVariable String sDate,@PathVariable String eDate){

        
        LocalDate startDate = LocalDate.parse(sDate);
        LocalDate endDate = LocalDate.parse(eDate);
        List<Order> orders = orderRepository.findAllByOrderDateBetween(startDate,endDate);
        List<OrderResponse> orderList = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
			Order singleOrder = orders.get(i);
            
            if(!singleOrder.getStatus().equals("Törölve")){
                OrderResponse singleOrderResponse = new OrderResponse();
                singleOrderResponse.setId(singleOrder.getId());
                singleOrderResponse.setOrderdate(singleOrder.getOrderDate().toString());
                singleOrderResponse.setStatus(singleOrder.getStatus());
                singleOrderResponse.setTotalprice(singleOrder.getTotalPrice());
                singleOrderResponse.setCustomerid(singleOrder.getUser().getId());
                orderList.add(singleOrderResponse);
            }
			
            
		}
        return orderList;

	}

}
