package hu.sze.szakdolgozat.market.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProducerResponse {

    
    private String firstname;
    
    private String lastname;
    
    private String businessname;
    
    private String producerimage;
    @Column(name ="description",columnDefinition="TEXT")
    private String description;


}
