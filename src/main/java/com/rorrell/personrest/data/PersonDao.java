/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest.data;

import com.rorrell.personrest.model.Person;
import java.util.List;

/**
 *
 * @author rachel
 */
public interface PersonDao {

    Person findById(long id);

    List<Person> findOlder(int age);

    List<Person> findOppositeGender(String gender);

    List<Person> findSameGender(String gender);

    List<Person> findYounger(int age);

    List<Person> getAll();
    
}
