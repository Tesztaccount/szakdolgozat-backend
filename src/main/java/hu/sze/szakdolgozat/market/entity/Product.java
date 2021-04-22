package hu.sze.szakdolgozat.market.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;
    @Column(name ="productname")
    private String productname;
    @Column(name ="category")
    private String category;
    @Column(name ="unit")
    private String unit;
    @Column(name ="price")
    private Integer price;
    @Column(name ="details")
    private String details;
    
    // @Column(name = "image", length = 1000)
	// private byte[] image;

    @Column(name ="image", columnDefinition="LONGTEXT")
    private String image;

    @ManyToOne
    @JoinColumn(name="producer_id",referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderDetail> orderDetails;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductReview> productReviews;

}
