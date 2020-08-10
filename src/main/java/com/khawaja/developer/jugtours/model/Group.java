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
public class Group {
	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	private String name;
	private String city;
	private String stateOrProvince;
    private String country;
    private String postalCode;
    //Many users can go to same event. 
    //CascadeTypePersist makes a short-lasting instance persistent. Eg. propogates persist operation
    //From parent to child entity. When we save the x entity, y entity is saved.
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    //one Event can have many user
    //Eager will default load all relationships related to a particular object
    //CascadeTypeAll propogates all operations from parent to child
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Event> events;
}