package com.ensa.gi4.aspects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AspectUtils {
    final static String IP_ADDRESS;
    final static String[] LOG_FILES = new String[]{"database.log", "auth.log", "error.log"};
    final static String ERROR_LOG_FORMAT = "%s - [%s][%s] %s\t%s\t%s\n";
    final static String INFO_LOG_FORMAT = "%s - [%s][%s] %s\n";
    final static SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    static {
        InetAddress ip = null;
        try {
            ip = Inet4Address.getLocalHost();

        } catch (UnknownHostException ignore) { }
        if (ip != null)
            IP_ADDRESS = ip.toString();
        else
            IP_ADDRESS = "NOT CONNECTED";

    }
    @Value("${logs.folder}")
    private String logsFolder;

    private static String LOG_FOLDER;

    @Value("${logs.folder}")
    public void setNameStatic(String logsFolder){
        AspectUtils.LOG_FOLDER = logsFolder;
    }

    @PostConstruct
    public void init() throws IOException {

        File logFolder = new File(LOG_FOLDER);
        if(!logFolder.exists())
            logFolder.mkdirs();

        for(String log : LOG_FILES){
            new File(LOG_FOLDER + log).createNewFile();
        }
    }



    public static String getCurrentDateTime(){
        return dateformatter.format(new Date());
    }

    public static String getLogFilePath(int id){
        return LOG_FOLDER + LOG_FILES[id];
    }
}
