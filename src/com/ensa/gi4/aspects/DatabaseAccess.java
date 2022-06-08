package com.ensa.gi4.aspects;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.ensa.gi4.aspects.AspectUtils.*;

@Aspect
@Component

public class DatabaseAccess {
    @Autowired
    UserDao userDao;
    @Autowired
    MaterielDao materielDao;

    @AfterReturning(value = "execution( * com..DatabaseConfig.dataSource() )" ,returning = "dataSource")
    public void connectingToDatabase(DataSource dataSource) throws IOException {
        String LogFile = getLogFilePath(0);
        BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true));
        DriverManagerDataSource dmrDataSource = (DriverManagerDataSource) dataSource;
        String message = String.format("Accessing to database : url:%s\tusername:%s", dmrDataSource.getUrl(), dmrDataSource.getUsername());
        writer.write(String.format(INFO_LOG_FORMAT, IP_ADDRESS, getCurrentDateTime(), "INFO", message));
        writer.close();
    }


    @After(value = "execution( * com.ensa.gi4.datatabase.impl.*.save(..)) && args(entity)")
    public void databaseSave(JoinPoint jp, Object entity) throws IOException {
        String LogFile = getLogFilePath(0);
        BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true));
        writer.write(String.format(INFO_LOG_FORMAT, IP_ADDRESS, getCurrentDateTime(), "INFO", "SAVED : " + entity));
        writer.close();
    }

    @Before(value = "execution( * com.ensa.gi4.datatabase.impl.*.delete(..)) && args(id)")
    public void databaseDelete(JoinPoint jp, int id) throws IOException {
        Object deleted = null;
        if (jp.getSignature().getClass().getSimpleName().contains("User")){
            deleted = this.userDao.findOneById((long) id);
        }else{
            deleted = this.materielDao.findOne(id);
        }
        String LogFile = getLogFilePath(0);
        BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true));
        writer.write(String.format(INFO_LOG_FORMAT, getCurrentDateTime(), IP_ADDRESS, "INFO", "DELETED : " + deleted));
        writer.close();
    }
}
