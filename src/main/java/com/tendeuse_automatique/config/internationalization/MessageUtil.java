package com.tendeuse_automatique.config.internationalization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

/**
 *  class utilitaire pour simplifier la recuperation d'un message d'erreur externalisé
 */

@Component
@Slf4j
public class MessageUtil {

    private final MessageSource messageSource;

    public MessageUtil(){
        var source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");
        source.setDefaultEncoding("UTF-8");
        this.messageSource = source;
    }

    /**
     * Retourne la valeur du message à partir de son identifiant
     * @param key
     * @return message d'erreur
     */

    public String getMessage(String key){
        return this.getMessage(key,(Object) null);
    }

    public String getMessage(String key,Object...params){

        String translatedText = null;

        if(Objects.nonNull(messageSource)){
            try {
                translatedText = messageSource.getMessage(key,params, Locale.FRANCE);
            } catch (NoSuchMessageException e){
                log.warn("Impossible de récuperer ce message d'erreur, l'identifiant {} ne correspond à aucun message",key);
            }
        }else{
            log.warn("Impossible de récuperer ce message d'erreur par cet identifiant {} ,aucun fichier messages.properties n'a été trouvé pour la langue {}",key,Locale.FRANCE);
        }

        if(translatedText == null || translatedText.isEmpty()){
            translatedText = key.replace('.',' ');
        }

        return translatedText;
    }


}
