package com.ensa.gi4.service.impl;

import com.ensa.gi4.service.api.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Service
public class I18nServiceImpl implements I18nService {

    @Autowired
    private ResourceBundleMessageSource resource;
    public static String[] LANGUAGES_LIST = new String[]{"FR", "EN"};

    private String language;

    @PostConstruct
    public void init(){
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
