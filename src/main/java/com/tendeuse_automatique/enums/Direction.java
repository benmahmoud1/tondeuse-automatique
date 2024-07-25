package com.tendeuse_automatique.enums;

import com.tendeuse_automatique.constants.ErrorConstants;
import com.tendeuse_automatique.exception.TondeuseException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Direction {

    N('N',"Nord"),
    S('S',"Sud"),
    E('E',"Est"),
    W('W',"Ouest");

    private final Character name;
    private final String libelle;

    Direction(char name,String libelle){
        this.name = name;
        this.libelle = libelle;
    }

    public static Direction getDirectionFromName(Character name) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> TondeuseException.from(ErrorConstants.TE_1001,name));
    }


}
