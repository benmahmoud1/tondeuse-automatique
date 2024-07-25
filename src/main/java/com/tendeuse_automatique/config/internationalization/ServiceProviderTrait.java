package com.tendeuse_automatique.config.internationalization;


import com.tendeuse_automatique.config.context.SpringApplicationContext;

public interface ServiceProviderTrait {

    static <T> T withService(Class<T> serviceBean){

        return SpringApplicationContext.getBeanByType(serviceBean);
    }
}
