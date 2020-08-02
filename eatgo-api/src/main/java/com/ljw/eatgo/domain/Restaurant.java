package com.ljw.eatgo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    private  String name;
    private  String address;
 
    @Transient //무시하고 통과
    private List<MenuItem> menuItems;


    public Restaurant(Long id, String name, String address) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Restaurant(String name, String address) {
        this.name=name;
        this.address=address;
    }



    public String getInfomation() {
        return name + " in " + address;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>();
        for(MenuItem menuItem:menuItems){
            addMenuItem(menuItem);
        }
    }



    public void updateInformation(String name, String address) {
        this.name=name;
        this.address=address;
    }
}
