package com.ljw.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private  String name;
    private  String address;
    private  Long id;
    private List<MenuItem> menuItems=new ArrayList<MenuItem>();

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Restaurant(String name, String address) {
        this.name=name;
        this.address=address;
    }


    public void setId(long id) {
        this.id=id;
    }
    public String getName() {
        return name;
    }

    public String getInfomation() {
        return name + " in " + address;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem:menuItems){
            addMenuItem(menuItem);
        }
    }

}
