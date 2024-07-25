package com.tendeuse_automatique.exception;

import com.tendeuse_automatique.config.internationalization.GlobaleExceptionConfig;
import com.tendeuse_automatique.constants.ErrorConstants;
import org.zalando.problem.Status;

import static com.tendeuse_automatique.config.internationalization.InternationalizationProviderTrait.translate;

public class TondeuseException extends GlobaleExceptionConfig {

    public TondeuseException(String code, String message) {
        super(code,  ErrorConstants.FUNCTIONAL_TITLE_ERROR, Status.BAD_REQUEST, message);
    }

    public static TondeuseException from(String code , Object...args){
        return new TondeuseException(code , translate(code,args));
    }
}
