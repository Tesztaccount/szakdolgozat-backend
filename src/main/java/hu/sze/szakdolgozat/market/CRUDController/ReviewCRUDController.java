package hu.sze.szakdolgozat.market.CRUDController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sze.szakdolgozat.market.dao.ProducerReviewRepository;
import hu.sze.szakdolgozat.market.dao.ProductReviewRepository;
import hu.sze.szakdolgozat.market.dto.ReviewResponse;
import hu.sze.szakdolgozat.market.entity.ProducerReview;
import hu.sze.szakdolgozat.market.entity.ProductReview;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class ReviewCRUDController {

    @Autowired
	private ProducerReviewRepository producerReviewRepository;
	@Autowired
	private ProductReviewRepository productReviewRepository;

    @GetMapping("/producerReviews")
	public List<ReviewResponse> producerReviews() {

        List<ReviewResponse> reviewList = new ArrayList<>();
		List<ProducerReview> tempReview = producerReviewRepository.findAll();
		for (int i = 0; i < tempReview.size(); i++) {

			ProducerReview singleReview = tempReview.get(i);
			ReviewResponse reviewResponse = new ReviewResponse();
			reviewResponse.setId(singleReview.getId());
			reviewResponse.setReviewdId(singleReview.getUser().getId());
            reviewResponse.setReview(singleReview.getReview());
			reviewList.add(reviewResponse);

		}
		return reviewList;

    }

	@GetMapping("/productReviews")
	public List<ReviewResponse> productReviews() {

        List<ReviewResponse> reviewList = new ArrayList<>();
		List<ProductReview> tempReview = productReviewRepository.findAll();
		for (int i = 0; i < tempReview.size(); i++) {

			ProductReview singleReview = tempReview.get(i);
			ReviewResponse reviewResponse = new ReviewResponse();
			reviewResponse.setId(singleReview.getId());
			reviewResponse.setReviewdId(singleReview.getProduct().getId());
            reviewResponse.setReview(singleReview.getReview());
			reviewList.add(reviewResponse);

		}
		return reviewList;

    }

	@DeleteMapping("/deleteProducerReview/{id}")
	public String deleteProducerReview(@PathVariable Integer id) {

        producerReviewRepository.deleteById(id);
        return "deleted";
	}

	@DeleteMapping("/deleteProductReview/{id}")
	public String deleteProductReview(@PathVariable Integer id) {

        productReviewRepository.deleteById(id);
        return "deleted";
	}
    
}
