package com.ljw.eatgo.interfaces;

import com.ljw.eatgo.application.MenuItemService;
import com.ljw.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;


    //http PATCH localhost:8080/restaurants/3/menuitems < menuitems.json
    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable Long restaurantId, @RequestBody List<MenuItem> menuItems){
//        List<MenuItem> menuItems=new ArrayList<>();
        menuItemService.bulkUpdate(restaurantId,menuItems);
        return "";
    }
}
