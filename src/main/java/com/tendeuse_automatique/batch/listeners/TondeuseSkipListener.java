package com.tendeuse_automatique.batch.listeners;

import com.tendeuse_automatique.dto.TondeuseInputDto;
import com.tendeuse_automatique.entity.Tondeuse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe permet de tracer seulement les lignes SKIPPED (les cas où on leve une exception)
 * Exemple pour le cas d'une Direction inexistante innexistant
 */
@Slf4j
@Getter
@Component
public class TondeuseSkipListener implements SkipListener<TondeuseInputDto, Tondeuse> {

    private final Set<Integer> skippableOffset = new HashSet<>();

    @Override
    public void onSkipInRead(Throwable t) {
        //aucun traitement
    }

    @Override
    public void onSkipInWrite(Tondeuse item, Throwable t) {
        //aucun traitement
    }

    /**
     * Tracer tous les cas de skipped item "aucun contrôle sur le type de l'exception"
     *
     * @param item the failed item
     * @param t    the cause of the failure
     */
    @Override
    public void onSkipInProcess(TondeuseInputDto item, Throwable t) {
        skippableOffset.add(item.getOffset());
        log.warn("Skipped item {} dans la line  {}", item, item.getOffset());

    }
}
