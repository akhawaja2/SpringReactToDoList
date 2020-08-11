//The GroupRepository is in charge of managing our group entity.
package com.khawaja.developer.jugtours.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<ListItem, Long>
{
    List findByName(String name);
}