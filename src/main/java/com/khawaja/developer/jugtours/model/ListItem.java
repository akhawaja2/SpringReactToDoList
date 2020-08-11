package com.khawaja.developer.jugtours.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;


//shortcut to bundle features of tostr/equals/getter/setter together.
@Data
//Generates constructor with 0 parameters
@NoArgsConstructor
//Constructor with 1 parameter for each field that requires special handling
//https://projectlombok.org/features/constructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_group")
public class ListItem {
	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	private String name;
	private int priority;
    private boolean completed;
    private String dateAdded;
    private String notes;

    //Many users can go to same event. 
    //CascadeTypePersist makes a short-lasting instance persistent. Eg. propogates persist operation
    //From parent to child entity. When we save the x entity, y entity is saved.
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

}