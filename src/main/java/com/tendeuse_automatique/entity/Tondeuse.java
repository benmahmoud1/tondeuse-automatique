package com.tendeuse_automatique.entity;

import com.tendeuse_automatique.enums.Direction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class Tondeuse {


    private Long id;


    @Min(value = 0, message = "La valeur doit être positive ou zéro.")
    private int coordonnesX;


    @Min(value = 0, message = "La valeur doit être positive ou zéro.")
    private int coordonnesY;


    private String positionInitiale;


    private Direction directionInitiale;


    @NotEmpty(message = "les instructions ne doivent pas etre vide")
    @NotEmpty(message = "les instructions ne doivent pas etre null")
    @Pattern(regexp = "^[A-Z]+$", message = "La chaîne d'instructions doit contenir uniquement des lettres majuscules.")
    private String instructions;


    private String positionFinale;


    private Direction directionFinale;


    private Surface surface;

    public char[] getInstructionsArray(String instructions){
        return instructions.toCharArray();
    }


}
