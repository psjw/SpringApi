package com.ljw.eatgo.application;

import com.ljw.eatgo.domain.MenuItem;
import com.ljw.eatgo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest {

    private MenuItemService menuItemService;
    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        menuItemService=new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){

        List<MenuItem> menuitems=new ArrayList<>();
        menuitems.add(MenuItem.builder().name("Kimchi").build());
        menuitems.add(MenuItem.builder().id(12L).name("Gukbob").build());
        menuitems.add(MenuItem.builder().id(1004L).destroy(true).build());
        menuItemService.bulkUpdate(1L, menuitems);

//        verify(menuItemRepository).save(any());
        //times(2) 2번 실행
        verify(menuItemRepository,times(2)).save(any());
        verify(menuItemRepository,times(1)).deleteById(eq(1004L));

    }

}