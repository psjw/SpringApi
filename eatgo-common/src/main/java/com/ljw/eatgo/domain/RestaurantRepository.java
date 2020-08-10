package com.ljw.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id); // Optional<Restaurant> Null 처리가 아니라 Restaurant가 있냐 없냐를 체크

    Restaurant save(Restaurant restaurant);
}
