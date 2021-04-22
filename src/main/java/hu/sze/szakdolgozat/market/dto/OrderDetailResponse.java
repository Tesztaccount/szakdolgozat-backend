package hu.sze.szakdolgozat.market.dto;

import lombok.Data;

@Data
public class OrderDetailResponse {
    
    private Long id;
    private Long orderid;
    private String productname;
    private String unittype;
    private Integer unitprice;
    private Integer quantity;
    private String status;
    private Integer productid;

     

}
