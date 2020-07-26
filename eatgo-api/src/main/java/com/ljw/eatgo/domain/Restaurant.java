package com.ljw.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private  String name;
    private  String address;
 
    @Transient //무시하고 통과
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
