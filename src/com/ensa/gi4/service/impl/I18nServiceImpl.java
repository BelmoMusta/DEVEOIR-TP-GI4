package com.ensa.gi4.service.impl;

import com.ensa.gi4.service.api.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class I18nServiceImpl implements I18nService {

    @Autowired
    private ResourceBundleMessageSource resource;
    public static String[] LANGUAGES_LIST;

    private String language;

    @PostConstruct
    public void init(){
        try {
            URI uri = this.getClass().getResource("/resources/messages/").toURI().normalize();
            String[] str = Stream.of(new File(uri).listFiles()).map(file -> file.getName().substring(6,8).toUpperCase()).toArray(String[]::new);
            LANGUAGES_LIST = str;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        this.language = Locale.getDefault().getLanguage().toLowerCase();
    }

    @Override
    public void setLanguage(String locale) {
        this.language = locale;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public String getText(String id){
        return resource.getMessage(id, null, Locale.forLanguageTag(this.language));
    }

    @Override
    public String getFormattedText(String id, Object... args) {
        return resource.getMessage(id, args, Locale.forLanguageTag(this.language));
    }


}
