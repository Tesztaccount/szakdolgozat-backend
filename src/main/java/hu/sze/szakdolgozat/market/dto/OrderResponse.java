package hu.sze.szakdolgozat.market.dto;

 

import lombok.Data;

@Data
public class OrderResponse {

    private Long id;
    private String orderdate;
    private String status;
    private Integer totalprice;
    private Integer customerid;
    
}
