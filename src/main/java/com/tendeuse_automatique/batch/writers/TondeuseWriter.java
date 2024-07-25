package com.tendeuse_automatique.batch.writers;

import com.tendeuse_automatique.entity.Tondeuse;
import com.tendeuse_automatique.service.ITondeuseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Writer permettant de sauvegarder les objets tendeuse dans un fichier output.txt
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class TondeuseWriter implements ItemWriter<Tondeuse> {

    private final ITondeuseService tondeuseService;


    @Override
    public void write(Chunk<? extends Tondeuse> items)  {

        List<Tondeuse> tendeuses = new ArrayList<>(items.getItems());

        tondeuseService.writeToFile(tendeuses);

    }

}
