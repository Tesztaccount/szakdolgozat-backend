package hu.sze.szakdolgozat.market.entity;

import java.util.Date;
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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "order_date", nullable = false)
    @CreationTimestamp
    private Date orderDate;

    @Column(name = "status", nullable = false)
    private String status;
    
    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
}
