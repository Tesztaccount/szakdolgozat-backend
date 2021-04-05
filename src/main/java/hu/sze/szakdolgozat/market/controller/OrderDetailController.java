package hu.sze.szakdolgozat.market.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dao.ProductRepository;
import hu.sze.szakdolgozat.market.dto.OrderDetailResponse;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;
import hu.sze.szakdolgozat.market.entity.Product;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/orderDetailList/{id}")
    public OrderDetailResponse listDetails(@PathVariable long id) {

        OrderDetailResponse singleResponse =  new OrderDetailResponse();

        List<OrderDetail> tempDetails = orderDetailRepository.findAll();
        for (int i = 0; i < tempDetails.size(); i++) {
            
            OrderDetail singleDetail = tempDetails.get(i);
            singleResponse.setId(singleDetail.getId());
            singleResponse.setProductname(singleDetail.getProductname());
            singleResponse.setQuantity(singleDetail.getQuantity());
            singleResponse.setStatus(singleDetail.getStatus());
            singleResponse.setProductid(singleDetail.getProduct().getId());
            singleResponse.setUnittype(singleDetail.getUnittype());

            int productId = singleDetail.getProduct().getId();
            Optional<Product> tempProduct = productRepository.findById(productId);
            Product asd = tempProduct.get();
            if(asd.getUser().getId() == id){

                return singleResponse;
                
            }
            
           
        }
        return singleResponse;

    }
}
