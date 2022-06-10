package com.ensa.gi4.service.api;





public interface I18nService {
    void setLanguage(String locale);
    String getLanguage();
    String getText(String id);
    String getFormattedText(String id, Object... args);
}
