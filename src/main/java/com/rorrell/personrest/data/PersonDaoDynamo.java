package com.rorrell.personrest.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.rorrell.personrest.model.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rachel
 */
public class PersonDaoDynamo implements PersonDao {

    private static PersonDaoDynamo orm;
    private static final DynamoDBMapper DB = DynamoDbConnector.getInstance().getDatabase();

    private enum Fields {
        ID(":id"),
        FIRSTNAME(":firstName"),
        LASTNAME(":lastName"),
        AGE(":age"),
        GENDER(":gender");

        private final String value;

        private Fields(String s) {
            value = s;
        }

        public String getValue() {
            return value;
        }

        public static Fields findByValue(String value) {
            for (Fields f : Fields.values()) {
                if (f.getValue().equals(value)) {
                    return f;
                }
            }
            return null;
        }
    }

    private PersonDaoDynamo() {
    }

    public static PersonDaoDynamo getInstance() {
        if (orm == null) {
            orm = new PersonDaoDynamo();
        }
        return orm;
    }

    private DynamoDBScanExpression getQuery(String conditionExpression, Pair... values) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        for (Pair p : values) {
            Fields key = Fields.findByValue(p.getKey().toString());
            switch (key) {
                case ID:
                case AGE:
                    eav.put(p.getKey().toString(), new AttributeValue()
                            .withN(p.getValue().toString()));
                    break;
                case FIRSTNAME:
                case LASTNAME:
                case GENDER:
                    eav.put(p.getKey().toString(), new AttributeValue()
                            .withS(p.getValue().toString()));
                    break;
            }
        }
        return new DynamoDBScanExpression()
                .withFilterExpression(conditionExpression)
                .withExpressionAttributeValues(eav);
    }

    @Override
    public Person findById(long id) {
        return DB.load(Person.class, id);
    }

    @Override
    public List<Person> findOlder(int age) {
        return DB.scan(Person.class, getQuery("age > " + Fields.AGE.getValue(),
                new Pair(Fields.AGE.getValue(), age)));
    }

    @Override
    public List<Person> findOppositeGender(String gender) {
        return DB.scan(Person.class, getQuery("gender <> " + Fields.GENDER.getValue(),
                new Pair(Fields.GENDER.getValue(), gender)));
    }

    @Override
    public List<Person> findSameGender(String gender) {
        return DB.scan(Person.class, getQuery("gender = " + Fields.GENDER.getValue(),
                new Pair(Fields.GENDER.getValue(), gender)));
    }

    @Override
    public List<Person> findYounger(int age) {
        return DB.scan(Person.class, getQuery("age < " + Fields.AGE.getValue(),
                new Pair(Fields.AGE.getValue(), age)));
    }

    @Override
    public List<Person> getAll() {
        List<Person> result = DB.scan(Person.class, new DynamoDBScanExpression());
        List<Person> people = new ArrayList<Person>(result);
        Collections.sort(people);
        return people;
    }

}
