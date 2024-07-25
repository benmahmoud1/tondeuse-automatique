package com.tendeuse_automatique.entity;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class Surface {

    private Long id;

    @Min(value = 0, message = "La valeur doit être positive ou zéro.")
    private int longeurX;

    @Min(value = 0, message = "La valeur doit être positive ou zéro.")
    private int longeurY;

    private List<Tondeuse> tondeuses;
}
