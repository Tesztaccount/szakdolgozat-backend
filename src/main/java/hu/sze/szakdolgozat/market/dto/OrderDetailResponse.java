package hu.sze.szakdolgozat.market.dto;

import lombok.Data;

@Data
public class OrderDetailResponse {
    
    public Long id;
    public String productname;
    public String unittype;
    public Integer quantity;
    public String status;
    public Integer productid;

}
