package com.tendeuse_automatique.config.internationalization;

import jakarta.annotation.Nullable;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@Getter
public class GlobaleExceptionConfig extends AbstractThrowableProblem {

    private final String code;
    private final String title;
    private final String detail;
    private final Status status;

    public GlobaleExceptionConfig(final String code,@Nullable final String title, @Nullable final Status status, @Nullable final String detail){

        this.code = code;
        this.title = title;      // description courte de l'erreur
        this.detail = detail;    // description longue de l'erreur
        this.status = status;    // code d'erreur

    }

}
