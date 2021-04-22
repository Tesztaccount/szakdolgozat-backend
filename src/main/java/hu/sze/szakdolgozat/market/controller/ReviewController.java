package hu.sze.szakdolgozat.market.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.ProducerReviewRepository;
import hu.sze.szakdolgozat.market.dao.ProductReviewRepository;
import hu.sze.szakdolgozat.market.entity.ProducerReview;
import hu.sze.szakdolgozat.market.entity.ProductReview;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class ReviewController {

    @Autowired
    private ProductReviewRepository productReviewRepository;
    @Autowired
    private ProducerReviewRepository producerReviewRepository;

    @PostMapping("/postProductReview")
    public String postProductReview(@RequestBody ProductReview productReview){
        
        if(StringUtils.isNotBlank(productReview.getReview()) && productReview.getReview().length() < 500){
            productReviewRepository.save(productReview);
        }
        return "ok";

    }
    @PostMapping("/postProducerReview")
    public String postProducerReview(@RequestBody ProducerReview producerReview){

        if(StringUtils.isNotBlank(producerReview.getReview())){
            producerReviewRepository.save(producerReview);
        }
        return "ok";

    }
}
