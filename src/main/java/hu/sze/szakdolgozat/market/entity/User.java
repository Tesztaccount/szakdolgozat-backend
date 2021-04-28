package hu.sze.szakdolgozat.market.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="firstname", nullable = false)
    private String firstname;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="role", nullable = false)
    private String role;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phonenumber", length = 11, nullable = false)
    private String phonenumber;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name ="image", columnDefinition="LONGTEXT")
    private String image;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Order> order;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Product> product;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<ProducerReview> producerReviews;
     
}
