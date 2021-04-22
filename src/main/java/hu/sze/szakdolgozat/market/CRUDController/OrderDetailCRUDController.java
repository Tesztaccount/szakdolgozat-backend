package hu.sze.szakdolgozat.market.CRUDController;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dao.ProductRepository;
import hu.sze.szakdolgozat.market.dto.OrderDetailResponse;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;
import hu.sze.szakdolgozat.market.entity.Product;
import hu.sze.szakdolgozat.market.service.OrderService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class OrderDetailCRUDController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;

    @PutMapping("/editOrderDetails/{id}")
    public String editOrderDetails(@RequestBody OrderDetailResponse orderDetail, @PathVariable("id") Long id) {

        Optional<OrderDetail> singleOrderDetail = orderDetailRepository.findById(id);
        OrderDetail tempOrderDetail = singleOrderDetail.get();
        Optional<Product> tempProduct = productRepository.findById(orderDetail.getProductid());

        Product singleProduct = tempProduct.get();

        tempOrderDetail.setProductname(orderDetail.getProductname());
        tempOrderDetail.setQuantity(orderDetail.getQuantity());
        tempOrderDetail.setStatus(orderDetail.getStatus());
        tempOrderDetail.setUnittype(orderDetail.getUnittype());
        tempOrderDetail.setUnitprice(orderDetail.getUnitprice());
        tempOrderDetail.setProduct(singleProduct);
        orderDetailRepository.save(tempOrderDetail);

        Order order = tempOrderDetail.getOrder();
        orderService.orderRefresh(order.getId());
       
        return "ok";

    }

    @GetMapping("/orderDetailsList/{id}")
    public List<OrderDetailResponse> orderDetailList(@PathVariable("id") Long id) {

        List<OrderDetailResponse> detailsList = new ArrayList<>();
        List<OrderDetail> tempDetails = orderDetailRepository.findAll();
        for (int i = 0; i < tempDetails.size(); i++) {

            OrderDetail singleDetail = tempDetails.get(i);
            if (singleDetail.getOrder().getId().equals(id)) {
                OrderDetailResponse singleResponse = new OrderDetailResponse();
                singleResponse.setId(singleDetail.getId());
                singleResponse.setOrderid(singleDetail.getOrder().getId());
                singleResponse.setProductname(singleDetail.getProductname());
                singleResponse.setQuantity(singleDetail.getQuantity());
                singleResponse.setStatus(singleDetail.getStatus());
                singleResponse.setProductid(singleDetail.getProduct().getId());
                singleResponse.setUnittype(singleDetail.getUnittype());
                singleResponse.setUnitprice(singleDetail.getUnitprice());
                detailsList.add(singleResponse);
            }

        }
        return detailsList;
    }

    @GetMapping("/myOrderDetailList/{id}")
    public List<OrderDetailResponse> listDetails(@PathVariable long id) {

        List<OrderDetailResponse> detailsList = new ArrayList<>();
        List<OrderDetail> tempDetails = orderDetailRepository.findAll();
        for (int i = 0; i < tempDetails.size(); i++) {

            OrderDetail singleDetail = tempDetails.get(i);

            int productId = singleDetail.getProduct().getId();
            Optional<Product> tempProduct = productRepository.findById(productId);
            if(tempProduct.get().getUser().getId() == id){

                OrderDetailResponse singleResponse =   new OrderDetailResponse();
                singleResponse.setId(singleDetail.getId());
                singleResponse.setOrderid(singleDetail.getOrder().getId());
                singleResponse.setProductname(singleDetail.getProductname());
                singleResponse.setQuantity(singleDetail.getQuantity());
                singleResponse.setStatus(singleDetail.getStatus());
                singleResponse.setProductid(singleDetail.getProduct().getId());
                singleResponse.setUnitprice(singleDetail.getUnitprice());
                singleResponse.setUnittype(singleDetail.getUnittype());
                detailsList.add(singleResponse);
            }      
        }
        return detailsList;

    }

    @GetMapping("/orderDetail/{id}")
    public OrderDetailResponse orderDetail(@PathVariable("id") Long id) {

        Optional<OrderDetail> tempDetails = orderDetailRepository.findById(id);
        OrderDetail singleDetail = tempDetails.get();
        OrderDetailResponse singleResponse = new OrderDetailResponse();

        singleResponse.setId(singleDetail.getId());
        singleResponse.setOrderid(singleDetail.getOrder().getId());
        singleResponse.setProductname(singleDetail.getProductname());
        singleResponse.setQuantity(singleDetail.getQuantity());
        singleResponse.setStatus(singleDetail.getStatus());
        singleResponse.setProductid(singleDetail.getProduct().getId());
        singleResponse.setUnittype(singleDetail.getUnittype());
        singleResponse.setUnitprice(singleDetail.getUnitprice());

        return singleResponse;
    }

    @DeleteMapping("/deleteDetail/{id}")
	public String delete(@PathVariable Long id) {

        Optional<OrderDetail> tempOrderDetail = orderDetailRepository.findById(id);
        OrderDetail tOrderDetail = tempOrderDetail.get();
        Order order = tOrderDetail.getOrder();
        orderDetailRepository.deleteById(id);
        orderService.orderRefresh(order.getId());

        if(order.getOrderDetails().isEmpty()){
            orderRepository.delete(order);
        }
        
        return "deleted";
	}
}
