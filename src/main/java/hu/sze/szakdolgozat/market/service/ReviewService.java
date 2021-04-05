package hu.sze.szakdolgozat.market.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.OrderRepository;
import hu.sze.szakdolgozat.market.dao.ProducerReviewRepository;
import hu.sze.szakdolgozat.market.dao.ProductReviewRepository;
import hu.sze.szakdolgozat.market.entity.Order;
import hu.sze.szakdolgozat.market.entity.OrderDetail;
import hu.sze.szakdolgozat.market.entity.ProducerReview;
import hu.sze.szakdolgozat.market.entity.ProductReview;

@Service
public class ReviewService {
    
    private ProductReviewRepository productReviewRepository;
    private ProducerReviewRepository producerReviewRepository; 

    public ReviewService(ProductReviewRepository productReviewRepository,ProducerReviewRepository producerReviewRepository){
        this.productReviewRepository = productReviewRepository;
        this.producerReviewRepository = producerReviewRepository;
        
    }

    @Transactional
    public ResponseEntity<Object> addProductReview(ProductReview productReview){

        ProductReview newProductReview = new ProductReview();
        
        newProductReview.setProduct(productReview.getProduct());
        newProductReview.setReview(productReview.getReview());

        ProductReview savedProductReview = productReviewRepository.save(newProductReview);
        if(productReviewRepository.findById(savedProductReview.getId()).isPresent()){
            return ResponseEntity.ok().body(savedProductReview);
        }else
            return ResponseEntity.unprocessableEntity().body("Failed to create review");
    }

    public ResponseEntity<Object> addProducerReview(ProducerReview producerReview) {
      
        ProducerReview newProducerReview = new ProducerReview();

        newProducerReview.setUser(producerReview.getUser());
        newProducerReview.setReview(producerReview.getReview());

        ProducerReview savedProducerReview = producerReviewRepository.save(newProducerReview);
        if(producerReviewRepository.findById(savedProducerReview.getId()).isPresent()){
            return ResponseEntity.ok().body(savedProducerReview);
        }else
            return ResponseEntity.unprocessableEntity().body("Failed to create review");

    }

}
