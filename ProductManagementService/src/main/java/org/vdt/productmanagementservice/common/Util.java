package org.vdt.productmanagementservice.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Util {
    private final MessageSource messageSource;
    public String getMessageFromMessageSource(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
