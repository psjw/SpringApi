package com.ljw.eatgo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void list() throws Exception {
        //Ctrl Option O import정리
        mvc.perform(MockMvcRequestBuilders.get("/restaurants")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")));


    }

    @Test
    public void detail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/restaurants/1004")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/2020")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2020")))
                .andExpect(content().string(containsString("\"name\":\"Cyber Food\"")));
    }

}