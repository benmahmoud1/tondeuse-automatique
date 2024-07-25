package com.tendeuse_automatique.config.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;

public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {

        context = appContext;
    }

    public static Object getBean(String beanName){

        return context != null ? context.getBean(beanName) : null;
    }

    public static <T>  T getBeanByType(Class<T> type){

        Collection<T> beans = context.getBeansOfType(type).values();
        return beans.stream().findFirst().orElse(null);
    }
}
