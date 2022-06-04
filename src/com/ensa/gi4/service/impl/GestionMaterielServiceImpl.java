package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Entity;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GestionMaterielServiceImpl implements GestionMaterielService {

    @Value("${materiel.row.format}")
    private String format;
    final MaterielDao materielDao;
    final LivreDao livreDao;
    final ChaiseDao chaiseDao;
    final I18nService i18nService;
    final EntityUtils entityUtils;


    @Override
    public void listerMateriel() {;
        System.out.format(format, i18nService.getText("table.header").split(","));
        materielDao.findAll().forEach(materiel -> {
            System.out.format(format, entityUtils.extractFromEntity(materiel, null));
        });
        livreDao.findAll();
    }

    @Override
    public void ajouterNouveauMateriel(String[] materielFields) {
        String type = materielFields[1];
        if ("LIVRE".equals(type)){
            Livre livre = new Livre();
            entityUtils.populateInputFields(livre, materielFields);
            livre = livreDao.save(livre);
            System.out.println(i18nService.getFormattedText("message.success.add", livre.getType(), livre.getId()));
        }else{
            Chaise chaise = new Chaise();
            entityUtils.populateInputFields(chaise, materielFields);
            chaise = chaiseDao.save(chaise);
            System.out.println(i18nService.getFormattedText("message.success.add", chaise.getType(), chaise.getId()));
        }

    }


}
