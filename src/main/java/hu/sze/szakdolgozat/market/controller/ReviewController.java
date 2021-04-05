package hu.sze.szakdolgozat.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.entity.ProducerReview;
import hu.sze.szakdolgozat.market.entity.ProductReview;
import hu.sze.szakdolgozat.market.service.ReviewService;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/auth")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService ReviewService){
        this.reviewService=ReviewService;
    }

    @PostMapping("/postProductReview")
    public ResponseEntity<Object> postProductReview(@RequestBody ProductReview productReview){

        return reviewService.addProductReview(productReview);

    }
    @PostMapping("/postProducerReview")
    public ResponseEntity<Object> postProducerReview(@RequestBody ProducerReview producerReview){

        return reviewService.addProducerReview(producerReview);

    }
}
