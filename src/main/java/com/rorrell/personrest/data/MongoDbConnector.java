/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest.data;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author rachel
 */
public class MongoDbConnector {
    
    private final MongoDatabase db;
    private static MongoDbConnector connector;

    private MongoDbConnector() {
        MongoClient client = new MongoClient("localhost", 27017);
        db = client.getDatabase("testrest");
    }
    
    public static MongoDbConnector getInstance() {
        if(connector == null)
            connector = new MongoDbConnector();
        return connector;
    }

    public MongoDatabase getDatabase() {
        return db;
    }
}
