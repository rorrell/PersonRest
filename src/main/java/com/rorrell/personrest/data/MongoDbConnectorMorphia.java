/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest.data;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author rachel
 */
public class MongoDbConnectorMorphia {
    
    private final Datastore ds;
    private static MongoDbConnectorMorphia connector;
    
    private MongoDbConnectorMorphia() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.rorrell.personrest");
        MongoClient client = new MongoClient("localhost", 27017);
        ds = morphia.createDatastore(client, "testrest");
    }
    
    public static MongoDbConnectorMorphia getInstance() {
        if(connector == null)
            connector = new MongoDbConnectorMorphia();
        return connector;
    }

    public Datastore getDatastore() {
        return ds;
    }
}
