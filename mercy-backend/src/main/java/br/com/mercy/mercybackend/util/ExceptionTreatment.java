package br.com.mercy.mercybackend.util;

import org.slf4j.Logger;

import br.com.mercy.mercybackend.response.Response;

public class ExceptionTreatment{
    public static void setExceptionMessage(String prefix, Exception e, Response<?> response, Logger log){
        String errorMsg = String.format("%s. %s. At line %d", e.getClass().getSimpleName(), e.getMessage(), e.getStackTrace()[0].getLineNumber());
        log.debug(prefix + errorMsg);
        response.setError("Failure: Internal Server Error 500. " + errorMsg);
    }
}