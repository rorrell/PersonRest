/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest.data;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author rachel
 */
public class PersonDaoDynamoTest {
    
    private final PersonDaoDynamo dao = PersonDaoDynamo.getInstance();
    
    public PersonDaoDynamoTest() {
    }

    /**
     * Test of getAll method, of class PersonDaoMorphia.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        int expResult = 7;
        int result = dao.getAll().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class PersonDaoMorphia.
     */
    @Test
    public void testGetAllSorted() {
        System.out.println("getAll - Sorted");
        long expResult = 1;
        long result = dao.getAll().get(0).getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class PersonDaoMorphia.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 4;
        String expResult = "River";
        String result = (dao.findById(id)).getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of findOlder method, of class PersonDaoMorphia.
     */
    @Test
    public void testFindOlder() {
        System.out.println("findOlder");
        int age = 30;
        int expResult = 4;
        int result = dao.findOlder(age).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findYounger method, of class PersonDaoMorphia.
     */
    @Test
    public void testFindYounger() {
        System.out.println("findYounger");
        int age = 30;
        int expResult = 3;
        int result = dao.findYounger(age).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findSameGender method, of class PersonDaoMorphia.
     */
    @Test
    public void testFindSameGender() {
        System.out.println("findSameGender");
        String gender = "f";
        int expResult = 3;
        int result = dao.findSameGender(gender).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findOppositeGender method, of class PersonDaoMorphia.
     */
    @Test
    public void testFindOppositeGender() {
        System.out.println("findOppositeGender");
        System.out.println("findOppositeGender");
        String gender = "f";
        int expResult = 4;
        int result = dao.findOppositeGender(gender).size();
        assertEquals(expResult, result);
    }
    
}
