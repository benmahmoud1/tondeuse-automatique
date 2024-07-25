package com.tendeuse_automatique.config.internationalization;

public interface InternationalizationProviderTrait {

    static String translate(String code){

        return ServiceProviderTrait.withService(MessageUtil.class).getMessage(code);
    }

    static String translate(String code, Object...args){

        return ServiceProviderTrait.withService(MessageUtil.class).getMessage(code, args);
    }
}
