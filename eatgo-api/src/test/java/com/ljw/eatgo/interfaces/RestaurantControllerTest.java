package com.ljw.eatgo.interfaces;

import com.ljw.eatgo.application.RestaurantService;
import com.ljw.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
        restaurants.add(new Restaurant(1004L,"JOKER House","Seoul"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);
        //Ctrl Option O import정리
        mvc.perform(MockMvcRequestBuilders.get("/restaurants")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("\"name\":\"JOKER House\"")));


    }

    @Test
    public void detail() throws Exception {
        Restaurant restuarant1=new Restaurant(1004L,"JOKER House","Seoul");
        restuarant1.setMenuItems(Arrays.asList(new MenuItem("Kimchi")));
        Restaurant restuarant2=new Restaurant(2020L,"Cyber Food","Seoul");
        restuarant2.setMenuItems(Arrays.asList(new MenuItem("Kimchi")));
        given(restaurantService.getRestaurant(1004L)).willReturn(restuarant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restuarant2);


        mvc.perform(MockMvcRequestBuilders.get("/restaurants/1004")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"JOKER House\"")))
                .andExpect(content().string(containsString("Kimchi")));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/2020")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2020")))
                .andExpect(content().string(containsString("\"name\":\"Cyber Food\"")));
    }


    @Test
    public void create() throws Exception {
        //Argument(s) are different! Wanted:
        //선언한 객체와 실행된 객체가 다르다
        //  Restaurant restaurant=new Restaurant(1234L,"BeRyong","Seoul"); 와  verify(restaurantService).addRestaurant(restaurant); 의
        //restaurant 가 다르다 -> any()를 통해 해결
       // Restaurant restaurant=new Restaurant(1234L,"BeRyong","Seoul");
        //{"name":"BeRyong","address":"Busan"}
        mvc.perform(MockMvcRequestBuilders.post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON) //JSON 타입이라는 것을 알려줌
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));

        //given  willReturn 을 확인 가능
        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JOKER Bar\",\"address\":\"Busan\"}"))
                .andExpect(status().isOk());
        verify(restaurantService).updateRestaurant(1004L,"JOKER Bar","Busan");
    }
}