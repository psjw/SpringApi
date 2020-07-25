package com.ljw.eatgo.interfaces;


import com.ljw.eatgo.application.RestaurantService;
import com.ljw.eatgo.domain.MenuItem;
import com.ljw.eatgo.domain.MenuItemRepository;
import com.ljw.eatgo.domain.Restaurant;
import com.ljw.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private RestaurantService restaurantSergvice;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
//        List<Restaurant> restaurants=new ArrayList<>();
//        restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));

        
//        List<Restaurant> restaurants= restaurantRepository.findAll();
        List<Restaurant> restaurants=restaurantSergvice.getRestaurants();
        return  restaurants;
    }

    @GetMapping("restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
//        List<Restaurant> restaurants=new ArrayList<>();
//
//        restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));
//        restaurants.add(new Restaurant(2020L,"Cyber Food","Seoul"));

        // 기본정보 + 메뉴정보
        Restaurant restaurant=restaurantSergvice.getRestaurant(id);

//        Restaurant restaurant= restaurantRepository.findById(id);
//        List<MenuItem> menuItems=menuItemRepository.findAllByRestaurantId(id);
//        restaurant.setMenuItems(menuItems);
//        restaurant.addMenuItem(new MenuItem("Kimchi"));
        return restaurant;
    }
}
