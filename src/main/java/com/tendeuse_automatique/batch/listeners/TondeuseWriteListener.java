package com.tendeuse_automatique.batch.listeners;

import com.tendeuse_automatique.entity.Tondeuse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TondeuseWriteListener implements ItemWriteListener<Tondeuse> {


    public void beforeWrite(List<? extends Tondeuse> item) {
        //aucun traitement
    }


    public void afterWrite(List<? extends Tondeuse> items) {
        //aucun traitement
    }


    public void onWriteError(Exception exception, List<? extends Tondeuse> items) {
        log.error("Erreur d'écriture de la liste [{}]", items.stream().map(Tondeuse::getDirectionInitiale).toList());
        log.debug("Détails de l'erreur d'écriture de la liste", exception);
    }
}
