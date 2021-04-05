package hu.sze.szakdolgozat.market.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import hu.sze.szakdolgozat.market.entity.ProducerReview;

@CrossOrigin("http://localhost:4200")
public interface ProducerReviewRepository extends JpaRepository<ProducerReview, Integer>{
    
}
