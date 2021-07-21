package com.employee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizationService {

    private final MessageSource messageSource;

    public String getLocalizationMessage(String message) {
        return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }

}
