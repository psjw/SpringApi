package com.ljw.eatgo.application;

import com.ljw.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository;


    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository=restaurantRepository;

    }

    public Restaurant getRestaurant(Long id){
//        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants=restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {
        //TODO:update Restaurant

        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);
        //restaurant.setId(id);
        //restaurant.setName(name);

        restaurant.updateInformation(name,address);

       // Restaurant restaurant=new Restaurant(id,name,address);
        return restaurant;
    }
}

