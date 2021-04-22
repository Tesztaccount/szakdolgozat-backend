package hu.sze.szakdolgozat.market.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Integer id;
    private String productname;
    private String unit;
    private Integer price;
    private String image;
    private String details;
    private String category;
    private Integer producer;


}
