package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl implements MessageService {

    private final MessageSource messages;

    @Autowired
    public MessageSourceServiceImpl(@Qualifier("messageSource") MessageSource messages) {
        this.messages = messages;
    }

    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, null, locale);
    }

}
