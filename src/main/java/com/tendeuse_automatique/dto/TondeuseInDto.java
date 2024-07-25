package com.tendeuse_automatique.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TondeuseInDto {

    private String surface;
    private String positionInitiale;
    private String instructions;
}
