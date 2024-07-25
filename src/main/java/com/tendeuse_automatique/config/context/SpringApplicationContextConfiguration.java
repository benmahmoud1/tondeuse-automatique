package com.tendeuse_automatique.config.context;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringApplicationContextConfiguration {

    private final ApplicationContext appContext;

    @Bean
    public SpringApplicationContext springApplicationContext(){

        final SpringApplicationContext springApplicationContext = new SpringApplicationContext();
        springApplicationContext.setApplicationContext(appContext);
        return springApplicationContext;
    }
}
