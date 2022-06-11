package com.ensa.gi4.service.test;


import com.ensa.gi4.AppGestionMateriel;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration(classes  = AppGestionMateriel.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GestionMaterielServiceTest {
    @Autowired
    MaterielDao materielDao;
    
    
    @Test
    public void testAdd() {
        int firstsize = materielDao.findAll().size();
        Materiel materiel=new Livre();
        materiel.setName("livreTest");
        materiel.setCode("LT");
        materiel.setDisponible(true);
        materiel.setIdM(3);
        materiel.setQte(2);
        materielDao.addOne(materiel);
        int newsize= materielDao.findAll().size();
        assertEquals("Le nouveau materiel n'a pas été ajouter", firstsize+1, newsize);
    }
    
     @Test
    public void testRemove() {
        int firstsize = materielDao.findAll().size();
        materielDao.removeOne("LI");
        int newsize= materielDao.findAll().size();
        assertEquals("Le nouveau materiel n'a pas été ajouter", firstsize-1, newsize);
    }

    
}
