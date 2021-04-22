package hu.sze.szakdolgozat.market.dto;

import lombok.Data;

@Data
public class StatResponse {
    
    private Integer numberOfCustomers;
    private Integer numberOfProducers;
    private Integer numberOfProducts;
    private Integer numberOfOrders;
    
}
