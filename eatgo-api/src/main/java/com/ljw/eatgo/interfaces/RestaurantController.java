package com.ljw.eatgo.interfaces;


import com.ljw.eatgo.domain.Restaurant;
import com.ljw.eatgo.domain.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class RestaurantController {


    private RestaurantRepository repositoty=new RestaurantRepository();

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
//        List<Restaurant> restaurants=new ArrayList<>();
//        restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));

        
        List<Restaurant> restaurants=repositoty.findAll();
        return  restaurants;
    }

    @GetMapping("restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
//        List<Restaurant> restaurants=new ArrayList<>();
//
//        restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));
//        restaurants.add(new Restaurant(2020L,"Cyber Food","Seoul"));
        Restaurant restaurant=repositoty.findById(id);


        return restaurant;
    }
}
