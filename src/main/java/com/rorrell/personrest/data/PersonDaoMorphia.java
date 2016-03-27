/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest.data;

import com.rorrell.personrest.model.Person;
import java.util.List;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author rachel
 */
public class PersonDaoMorphia implements PersonDao {
    private static final Datastore DS = MongoDbConnectorMorphia.getInstance().getDatastore();
    private static PersonDaoMorphia orm;
    

    private PersonDaoMorphia() {
    }

    public static PersonDaoMorphia getInstance() {
        if (orm == null) {
            orm = new PersonDaoMorphia();
        }
        return orm;
    }
    
    @Override
    public List<Person> getAll() {
        return DS.find(Person.class).asList();
    }
    
    @Override
    public Person findById(long id) {
        return DS.get(Person.class, id);
    }

    @Override
    public List<Person> findOlder(int age) {
        return DS.createQuery(Person.class).field("age").greaterThan(age).asList();
    }

    @Override
    public List<Person> findYounger(int age) {
        return DS.createQuery(Person.class).field("age").lessThan(age).asList();
    }

    @Override
    public List<Person> findSameGender(String gender) {
        return DS.createQuery(Person.class).field("gender").equal(gender).asList();
    }

    @Override
    public List<Person> findOppositeGender(String gender) {
        return DS.createQuery(Person.class).field("gender").notEqual(gender).asList();
    }
}
