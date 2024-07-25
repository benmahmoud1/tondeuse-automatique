package com.tendeuse_automatique.dto;

import com.tendeuse_automatique.enums.Direction;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TondeuseDto {


    private int coordonnesX;

    private int coordonnesY;

    private Direction directionInitiale;

    private String instructions;

    private SurfaceDto surfaceDto;
}
