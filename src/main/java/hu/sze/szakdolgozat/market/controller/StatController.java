package hu.sze.szakdolgozat.market.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threetenbp.ThreeTenBackPortJpaConverters.LocalDateConverter;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dao.ProductRepository;
import hu.sze.szakdolgozat.market.dao.UserRepository;
import hu.sze.szakdolgozat.market.dto.OrderResponse;
import hu.sze.szakdolgozat.market.dto.StatResponse;
import hu.sze.szakdolgozat.market.entity.Order;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class StatController {
    
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/stats")
	public List<OrderResponse> stats(){

        String date = "2021-04-21";
        String date2 = "2021-04-24";
        LocalDate localDate = LocalDate.parse(date);
        LocalDate localDate2 = LocalDate.parse(date2);
        List<Order> orders = orderRepository.findAllByOrderDateBetween(localDate,localDate2);
        List<OrderResponse> orderList = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
			Order singleOrder = orders.get(i);
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

}
