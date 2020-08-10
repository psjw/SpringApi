package com.ljw.eatgo.interfaces;

import com.ljw.eatgo.application.RestaurantService;
import com.ljw.eatgo.domain.MenuItem;
import com.ljw.eatgo.domain.Restaurant;
import com.ljw.eatgo.domain.RestaurantNotFoundException;
import com.ljw.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private  RestaurantService restaurantService;

//    @SpyBean(RestaurantRepositoryImpl.class) //Contorller에 원하는 객체 주입가능
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;
//
//    @SpyBean(RestaurantService.class)
//    private  RestaurantService restaurantService;



    @Test
    public void list() throws Exception {

        List<Restaurant> restaurants=new ArrayList<>();
//        restaurants.add(new Restaurant(1004L,"JOKER House","Seoul"));
        restaurants.add(Restaurant.builder().id(1004L).name("JOKER House").address("Seoul").build());
        given(restaurantService.getRestaurants()).willReturn(restaurants);
        //Ctrl Option O import정리
        mvc.perform(MockMvcRequestBuilders.get("/restaurants")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("\"name\":\"JOKER House\"")));


    }

    @Test
    public void detailIsExisted() throws Exception {
        Restaurant restuarant= Restaurant.builder().id(1004L).name("JOKER House").address("Seoul").build();
        MenuItem menuItem=MenuItem.builder().name("Kimchi").build();
        restuarant.setMenuItems(Arrays.asList(menuItem));
        Review review=Review.builder().name("JOKER").score(5).description("Great!").build();
//        Restaurant restuarant2=Restaurant.builder().id(2020L).name("Cyber Food").address("Seoul").build();
        restuarant.setReview(Arrays.asList(review));
        given(restaurantService.getRestaurant(1004L)).willReturn(restuarant);


        mvc.perform(MockMvcRequestBuilders.get("/restaurants/1004")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"JOKER House\"")))
                .andExpect(content().string(containsString("Kimchi")))
                .andExpect(content().string(containsString("Great!")));

    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404")).andExpect(status().isNotFound())
        .andExpect(content().string("{}"));
    }


}