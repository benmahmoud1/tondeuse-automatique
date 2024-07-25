package com.tondeuse_automatique.test.sample;

import com.tendeuse_automatique.entity.Surface;
import com.tendeuse_automatique.entity.Tondeuse;
import com.tendeuse_automatique.enums.Direction;



public final class TeudeuseSample {

    public static final Tondeuse TONDEUSE_1 = Tondeuse
            .builder()
            .id(1L)
            .coordonnesX(1)
            .coordonnesY(2)
            .directionInitiale(Direction.N)
            .instructions("GAGAGAGAA")
            .positionInitiale("1 2 N")
            .surface(Surface.builder().id(1L).longeurX(5).longeurY(5).build())
            .build();


    public static final Tondeuse TONDEUSE_3 = Tondeuse
            .builder()
            .id(3L)
            .coordonnesX(3)
            .coordonnesY(3)
            .directionInitiale(Direction.N)
            .instructions("GAGAGAGAA")
            .positionInitiale("1 2 N")
            .directionFinale(Direction.N)
            .surface(Surface.builder().id(1L).longeurX(5).longeurY(5).build())
            .build();



}
