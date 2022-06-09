package com.ensa.gi4.service.test;

import com.ensa.gi4.AppGestionMateriel;
import com.ensa.gi4.security.SessionHolder;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import enums.LoginResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppGestionMateriel.class, DatabaseTest.class})
@ActiveProfiles("test")
public class UserServiceTest {
    String[] validAdminCredential = new String[]{"root", "root"};
    String[] validUserCredential = new String[]{"mehdi0203", "root"};
    @Autowired
    ApplicationContext context;
    @Autowired
    UserService userService;
    @Autowired
    SessionHolder sessionHolder;
    @Autowired
    Environment environment;
    @Autowired
    I18nService i18nService;


    @Test
    public void userOperationsTest(){
        LoginResponse response;
        response = this.userService.loginUser(validAdminCredential[0], validAdminCredential[1]);
        assertEquals(LoginResponse.SUCCESS, response);
        response = this.userService.loginUser("root", "wrong");
        assertEquals(LoginResponse.PASSWORD_INCORRECT, response);
        response = this.userService.loginUser("wrong", "wrong");
        assertEquals(LoginResponse.USER_NOT_FOUND, response);
        this.userService.lockUser(2, true);
        response = this.userService.loginUser(validUserCredential[0], validUserCredential[1]);
        assertEquals(LoginResponse.ACCOUNT_LOCKED, response);
        this.userService.addUser("test", "test@gmail.com", "test");

    }

    @Test
    public void userSessionTest() throws InterruptedException {
        sessionHolder.setLoggedUser(null);
        sessionHolder.setExpiration("3");
        this.userService.loginUser(validAdminCredential[0], validAdminCredential[1]);
        assertFalse(userService.isUserExpired());
        Thread.sleep(4000);
        assertTrue(userService.isUserExpired());
    }

    @Test
    public void adminRestrictionTest(){
        String forbidden = i18nService.getText("message.info.forbidden");
        this.userService.lockUser(2, false);
        userService.loginUser(validUserCredential[0], validUserCredential[1]);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        userService.listUsers();
        String[] lines = baos.toString().split(System.lineSeparator());
        assertTrue(Arrays.asList(lines).contains(forbidden));
    }

    @Test
    public void adminGrantTest(){
        String forbidden = i18nService.getText("message.info.forbidden");
        userService.loginUser(validAdminCredential[0], validAdminCredential[1]);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        userService.listUsers();
        String[] lines = baos.toString().split(System.lineSeparator());
        assertFalse(Arrays.asList(lines).contains(forbidden));
    }
}
