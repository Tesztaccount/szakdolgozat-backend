package hu.sze.szakdolgozat.market.dto;

import lombok.Data;

@Data
public class FullUserResponse {
    
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String phonenumber;
    private String email;
    private String image;
    

}
