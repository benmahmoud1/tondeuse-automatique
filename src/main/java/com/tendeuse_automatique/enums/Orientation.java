package com.tendeuse_automatique.enums;

import lombok.Getter;

@Getter
public enum Orientation {

    G('G',"Gauche"),
    D('D',"Droite"),
    A('A',"Avancer");

    private final char name;
    private final String libelle;

    Orientation(Character name,String libelle){
        this.name = name;
        this.libelle = libelle;
    }
}
