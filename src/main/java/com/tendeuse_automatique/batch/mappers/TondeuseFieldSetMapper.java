package com.tendeuse_automatique.batch.mappers;

import com.tendeuse_automatique.dto.TondeuseInputDto;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


/**
 * FieldSetMapper pour convertir un flux de ligne du fichier  en un objet TendeuseInputDto
 */

public class TondeuseFieldSetMapper implements FieldSetMapper<TondeuseInputDto> {


    @Override
    public TondeuseInputDto mapFieldSet(FieldSet fieldSet) {
        return  TondeuseInputDto.builder()
                .line(String.join(" ", fieldSet.getValues()))
                .build();
    }
}
