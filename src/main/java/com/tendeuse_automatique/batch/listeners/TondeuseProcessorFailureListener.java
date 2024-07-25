package com.tendeuse_automatique.batch.listeners;

import com.tendeuse_automatique.dto.TondeuseInputDto;
import com.tendeuse_automatique.entity.Tondeuse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TondeuseProcessorFailureListener implements ItemProcessListener<TondeuseInputDto, Tondeuse> {

    @Override
    public void beforeProcess(TondeuseInputDto item) {
        //aucun traitement
    }

    @Override
    public void afterProcess(TondeuseInputDto item, Tondeuse result) {
        //aucun traitement
    }

    @Override
    public void onProcessError(TondeuseInputDto item, Exception exception) {
        log.error("Error lors du traitement de l'item [{}]", item, exception);
    }
}
