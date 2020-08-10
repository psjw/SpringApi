package com.ljw.eatgo.application;

import com.ljw.eatgo.domain.Review;
import com.ljw.eatgo.domain.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private ReviewRepository reviewRepository;


    public Review addReview(Long restaurantId,Review review) {
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }
}
