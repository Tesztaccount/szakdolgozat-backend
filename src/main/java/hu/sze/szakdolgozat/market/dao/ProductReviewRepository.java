package hu.sze.szakdolgozat.market.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import hu.sze.szakdolgozat.market.entity.ProductReview;

@CrossOrigin("http://localhost:4200")
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer>{
    
}
