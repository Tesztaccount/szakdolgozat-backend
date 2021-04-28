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
@Table(name="producer_reviews")
public class ProducerReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name ="review",columnDefinition="TEXT",nullable = false)
    private String review;

    @ManyToOne
    @JoinColumn(name="producer_id",referencedColumnName = "id")
    private User user;

}
