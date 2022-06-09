package com.ensa.gi4.service.test;

import com.ensa.gi4.AppGestionMateriel;
import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.security.SessionHolder;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.UserService;
import com.ensa.gi4.utils.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppGestionMateriel.class, DatabaseTest.class})
@ActiveProfiles("test")
public class GestionMaterielServiceTest {

    @Autowired
    private GestionMaterielService gestionMaterielService;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private EntityUtils entityUtils;
    @Autowired MaterielDao materielDao;

    @Before
    public void init(){
        UserDao userDao = context.getBean(UserDao.class);
        User user = userDao.findAll().get(0);
        SessionHolder sessionHolder = context.getBean(SessionHolder.class);
        sessionHolder.setLoggedUser(user);
        sessionHolder.setValidity(LocalDateTime.now());
    }

    @Test
    public void testAjouterMateriel() {
        LivreDao livreDao = context.getBean(LivreDao.class);
        String[] values = new String[]{"Antigone","LIVRE","Jean","Folio","5","5"};
        this.gestionMaterielService.ajouterNouveauMateriel(values);
        Livre livre = livreDao.findOne(livreDao.findMaxId());
        String[] fields = entityUtils.extractInputFields(livre);
        assertEquals(0,Arrays.compare(values,fields));
    }

    @Test
    public void testSupprimerMateriel(){
        int id = materielDao.findMaxId();
        assertNotNull(materielDao.findOne(id));
        this.gestionMaterielService.supprimerMateriel(id);
        assertNull(materielDao.findOne(id));
    }

    @Test
    public void testModifierMateriel(){
        int id = 1;
        //name, author, edition, stock, available
        InputStream backup = System.in;
        String userInput = String.format("test%stest%stest%s0%s0", System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        this.gestionMaterielService.modifierMateriel(id);
        Materiel materiel = materielDao.findOne(id);
        assertEquals("test", materiel.getName());
        System.setIn(backup);
    }
}
