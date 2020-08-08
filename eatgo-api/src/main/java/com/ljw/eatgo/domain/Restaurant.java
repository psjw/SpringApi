package com.ljw.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue
    private Long id;


    @NotEmpty
    private  String name;

    @NotEmpty
    private  String address;
 
    @Transient //무시하고 통과
    @JsonInclude(JsonInclude.Include.NON_NULL)//Null이 아닐때만 Json 에 넣어줌
    private List<MenuItem> menuItems;

//
//    public Restaurant(Long id, String name, String address) {
//        this.name = name;
//        this.address = address;
//        this.id = id;
//    }
//
//    public Restaurant(String name, String address) {
//        this.name=name;
//        this.address=address;
//    }



    public String getInfomation() {
        return name + " in " + address;
    }

//    public void addMenuItem(MenuItem menuItem){
//        menuItems.add(menuItem);
//    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        //this.menuItems=menuItems;
        //setMenuItems 호출시 참조값이 바뀌게 되므로 좋지 않다.
        this.menuItems = new ArrayList<>(menuItems);

    }



    public void updateInformation(String name, String address) {
        this.name=name;
        this.address=address;
    }
}
