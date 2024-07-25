package com.tendeuse_automatique.business.impl;

import com.tendeuse_automatique.business.ITondeuseBusiness;
import com.tendeuse_automatique.entity.Tondeuse;
import com.tendeuse_automatique.enums.Direction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TondeuseBusinessImpl implements ITondeuseBusiness {


    @Override
    public void executeInstructions(Tondeuse tondeuse) {
        tondeuse.setDirectionFinale(tondeuse.getDirectionInitiale());
        char[] instructionsArray = tondeuse.getInstructionsArray(tondeuse.getInstructions());
        for (char instruction :instructionsArray ) {
            switch (instruction) {
                case 'G':
                    toLeft(tondeuse);
                    break;
                case 'D':
                    toRight(tondeuse);
                    break;
                case 'A':
                    goOn(tondeuse);
                    break;
            }
        }

    }

    /**
     *
     * @param tondeuse
     * @return return un objet tondeuse avec sa nouvelle direction.
     *
     */

    @Override
    public Tondeuse toLeft(Tondeuse tondeuse) {
        Direction finalDirection = tondeuse.getDirectionFinale();
          switch (finalDirection) {
            case N:
                finalDirection = Direction.W;
                break;
            case E:
                finalDirection = Direction.N;

                break;
            case S:
                finalDirection = Direction.E;

                break;
            case W:
                finalDirection = Direction.S;

                break;
        }
        tondeuse.setDirectionFinale(finalDirection);
        return tondeuse;
    }

    /**
     *
     * @param tondeuse
     * @return return un objet tondeuse avec sa nouvelle direction.
     *
     */

    @Override
    public Tondeuse toRight(Tondeuse tondeuse) {
        Direction finalDirection = tondeuse.getDirectionFinale();
        switch (finalDirection) {
            case N:
                finalDirection = Direction.E;
                break;
            case E:
                finalDirection = Direction.S;
                break;
            case S:
                finalDirection = Direction.W;
                break;
            case W:
                finalDirection = Direction.N;
                break;
        }
        tondeuse.setDirectionFinale(finalDirection);
        return tondeuse;
    }

    /**
     *
     * @param tondeuse
     * @return return un objet tondeuse avec sa nouvelle position.
     * NB : si la direction est a (N ou S ) --> alors la tondeuse avance vers l'axe des Y
     * NB : si la direction est a (Eou W ) --> alors la tondeuse avance vers l'axe des X
     */

    @Override
    public Tondeuse goOn(Tondeuse tondeuse) {
        Direction direction = tondeuse.getDirectionFinale();
        int y = tondeuse.getCoordonnesY();
        int x = tondeuse.getCoordonnesX();;
        switch (direction) {
            case N:
                if (y < tondeuse.getSurface().getLongeurY()){
                    y = y+1;
                }
                break;
            case E:
                if (x < tondeuse.getSurface().getLongeurX()){
                    x = x+1;
            }
                break;
            case S:
                if (y > 0){
                    y = y-1;
                }
                break;
            case W:
                if (x > 0){
                    x = x-1;
                }
                break;
        }
        tondeuse.setCoordonnesX(x);
        tondeuse.setCoordonnesY(y);
        tondeuse.setPositionFinale(toCoordonnesXYAndFinalDirection(x,y, tondeuse.getDirectionFinale()));
        return tondeuse;
    }



    private String toCoordonnesXYAndFinalDirection(int X,int Y,Direction directionFinale) {
        return String.valueOf(X) + " " + String.valueOf(Y) + " " + directionFinale.getName() ;
    }
}
