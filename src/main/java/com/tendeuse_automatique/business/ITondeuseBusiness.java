package com.tendeuse_automatique.business;

import com.tendeuse_automatique.entity.Tondeuse;

public interface ITondeuseBusiness {

     void executeInstructions(Tondeuse tondeuse);

     Tondeuse toLeft(Tondeuse tondeuse);

     Tondeuse toRight(Tondeuse tondeuse);

     Tondeuse goOn(Tondeuse tondeuse);


}
