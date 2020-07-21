package com.ljw.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class RestaurantTests {
    @Test
    public void createion(){
        Restaurant restaurant=new Restaurant("Bob zip","Seoul");
        assertThat(restaurant.getName(),is("Bob zip"));
        assertThat(restaurant.getAddress(),is("Seoul"));
    }

    @Test
    public void infomation(){
        Restaurant restaurant=new Restaurant("Bob zip","Seoul");
        assertThat(restaurant.getInfomation(),is("Bob zip in Seoul"));
    }

}