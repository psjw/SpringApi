package com.ljw.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Caused by: java.lang.IllegalArgumentException: Not a managed type: class com.ljw.eatgo.domain.MenuItem
// -> @Entity 필요
@Entity
public class MenuItem {
    //Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaMappingContext': Invocation of init method failed; nested exception is org.hibernate.AnnotationException: No identifier specified for entity: com.ljw.eatgo.domain.MenuItem
    // ID가 없다.
    //하단의 두줄 추가
//    @Id
//    private Long id;
    @Id
    @GeneratedValue
    private Long id;



    private final String name;

    //Caused by: java.lang.IllegalArgumentException: Failed to create query for method public abstract java.util.List com.ljw.eatgo.domain.MenuItemRepository.
    // findAllByRestaurantId(java.lang.Long)! No property restaurantId found for type MenuItem!
    private Long restaurantId; //추가


    public MenuItem(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
