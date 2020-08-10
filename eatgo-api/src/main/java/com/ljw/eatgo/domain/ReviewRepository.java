package com.ljw.eatgo.domain;

import com.ljw.eatgo.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    Review save(Review review);

    List<Review> findAllByRestaurantId(Long restaurantId);
}
