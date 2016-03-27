/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest.data;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 *
 * @author rachel
 */
public class DynamoDbConnector {

    private static DynamoDbConnector connector;
    private final DynamoDBMapper db;

    private DynamoDbConnector() {
        BasicAWSCredentials cred = new BasicAWSCredentials("AKIAIBHWKCCV7G6N3MEA", "DVtEDwvUSS7xFVH4ATY0JohxhQLesJjD2V4i25kC");
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(cred);
        client.setEndpoint("dynamodb.us-west-2.amazonaws.com");
        db = new DynamoDBMapper(client);

    }

    public static DynamoDbConnector getInstance() {
        if (connector == null) {
            connector = new DynamoDbConnector();
        }
        return connector;
    }

    public DynamoDBMapper getDatabase() {
        return db;
    }

}
