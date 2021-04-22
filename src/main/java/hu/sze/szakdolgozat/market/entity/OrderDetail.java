package hu.sze.szakdolgozat.market.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="productname", nullable = false)
    private String productname;

    @Column(name="unittype", nullable = false)
    private String unittype;

    @Column(name="unit_price", nullable = false)
    private Integer unitprice;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @Column(name="status", nullable = false)
    private String status;

    @ManyToOne
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    

}