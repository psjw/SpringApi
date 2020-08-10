package com.ljw.eatgo.interfaces;

import com.ljw.eatgo.application.ReviewService;
import com.ljw.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService resviewService;

    @Test
    public void createWithValidAttributes() throws Exception {
        given(resviewService.addReview(eq(1L),any())).willReturn(Review.builder().id(1004L).build());

        mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"JOKER\",\"score\":3,\"description\":\"Mat-it-da\"}")).andExpect(status().isCreated())
        .andExpect(header().string("location", "/restaurants/1/reviews/1004"));
        verify(resviewService).addReview(eq(1L),any());

    }
    @Test
    public void createWithInvalidAttributes() throws Exception {
        given(resviewService.addReview(eq(1L),any())).willReturn(
                Review.builder().id(123L)
                        .name("JOKER")
                        .score(3)
                        .description("Mat-it-da")
                        .build()
        );


        mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest());
        //never()실행이 되지 않도록 함.
        verify(resviewService,never()).addReview(eq(1L),any());
    }
}