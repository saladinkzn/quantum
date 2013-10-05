package ru.kpfu.quantum.spring.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sala
 */
public abstract class ValidationUtils {
    public static void includeFieldsWithErrorsMap(HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        Map<String, Boolean> fieldsWithError = new HashMap<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldsWithError.put(fieldError.getField(), true);
        }
        httpServletRequest.setAttribute("fieldsWithError", fieldsWithError);
    }
}
