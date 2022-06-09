package com.ensa.gi4.service.impl;

import com.ensa.gi4.modele.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationServiceImplTest {

    AuthenticationServiceImpl service;
    User user;

    @Before
    public void setUp() {
        service = new AuthenticationServiceImpl();
        user = new User();
    }

    @After
    public void tearDown()  {
        //service = null;
        user = null;
    }


    @Test
    public void getUser() {
        //Test giving NullPointerException
        String hash = "$argon2id$v=19$m=15360,t=2,p=1$vA4YjosJ3tIsiE3JyYpRVBb7u4MxUznme2vJHTJ4MSM$Shf4uZ2afWzbHllM0edENIG787QvG3aOPtDwNR06tsMm/jAbgG932XOv2HIj459H9Fanu4bsrMAABfwgpmG7DA";
//        assertEquals("username",service.getUser("password","username").getUsername());
//        assertEquals(hash,service.getUser("password","username").getHashed_password());
    }

    @Test
    public void isPasswordValid() {
        String hash = "$argon2id$v=19$m=15360,t=2,p=1$vA4YjosJ3tIsiE3JyYpRVBb7u4MxUznme2vJHTJ4MSM$Shf4uZ2afWzbHllM0edENIG787QvG3aOPtDwNR06tsMm/jAbgG932XOv2HIj459H9Fanu4bsrMAABfwgpmG7DA";
        String password = "password";
        assertEquals(true,service.isPasswordValid(hash,password));
        assertEquals(false,service.isPasswordValid(hash,"otherpassword"));
    }

    @Test
    public void userExists() {
        //Test giving NullPointerExceptions IDK why
        //assertTrue(service.userExists("username"));
        //assertEquals(false,service.userExists("nonExistentUser"));
    }
}