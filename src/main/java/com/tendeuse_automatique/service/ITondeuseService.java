package com.tendeuse_automatique.service;

import com.tendeuse_automatique.entity.Tondeuse;

import java.util.List;

public interface ITondeuseService {

     void writeToFile(List<Tondeuse> tendeuses);

}
