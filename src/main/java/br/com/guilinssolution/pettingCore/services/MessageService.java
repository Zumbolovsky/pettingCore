package br.com.guilinssolution.pettingCore.services;

import java.util.Locale;

public interface MessageService {
    
    String getMessage(String key);
    
    String getMessage(String key, Object... attrs);
    
    String getMessage(String key, Locale locale);
    
}
