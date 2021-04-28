package hu.sze.szakdolgozat.market.CRUDController;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dto.OrderResponse;
import hu.sze.szakdolgozat.market.entity.Order;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class OrderCRUDController {

    @Autowired
    private OrderRepository orderRepository;
    
    @GetMapping("/orders")
    public List<OrderResponse> orderList(){

        List<OrderResponse> orderList = new ArrayList<>();
		List<Order> tempOrder = orderRepository.findAll();
       
        for (int i = 0; i < tempOrder.size(); i++) {
			Order singleOrder = tempOrder.get(i);
			OrderResponse singleOrderResponse = new OrderResponse();
			singleOrderResponse.setId(singleOrder.getId());
            singleOrderResponse.setOrderdate(singleOrder.getOrderDate().toString());
            singleOrderResponse.setStatus(singleOrder.getStatus());
            singleOrderResponse.setTotalprice(singleOrder.getTotalPrice());
            
            singleOrderResponse.setCustomerid(singleOrder.getUser().getId());
			 
			orderList.add(singleOrderResponse);
            
		}
        return orderList;
        
    }

    @DeleteMapping("/deleteOrder/{id}")
	public String delete(@PathVariable Long id) {

        Optional<Order> tempOrder = orderRepository.findById(id);
        Order order = tempOrder.get();
        order.setStatus("Törölve");
        orderRepository.save(order);
        return "deleted";
	}
    
}
