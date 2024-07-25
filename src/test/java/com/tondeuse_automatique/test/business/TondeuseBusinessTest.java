package com.tondeuse_automatique.test.business;

import com.tendeuse_automatique.business.impl.TondeuseBusinessImpl;
import com.tendeuse_automatique.enums.Direction;
import com.tondeuse_automatique.test.sample.TeudeuseSample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class TondeuseBusinessTest {

    @InjectMocks
    private TondeuseBusinessImpl tendeuseBusiness;


    @Test
    @DisplayName("testexecuteInstructions : doit retourner un objet tendeuse avec une nouvelle direction et nouvelle position")
    void testExecuteInstructions() {

        var tendeuse = TeudeuseSample.TONDEUSE_1;
        // When
        tendeuseBusiness.executeInstructions(tendeuse);

        // Then
        assertEquals(1, tendeuse.getCoordonnesX());
        assertEquals(3, tendeuse.getCoordonnesY());
        assertEquals(Direction.N, tendeuse.getDirectionFinale());

    }

    @Test
    @DisplayName("testToRight : doit retourner un objet tendeuse avec une nouvelle direction")
    void testToRight() {

        var tendeuse = TeudeuseSample.TONDEUSE_3;
        // When
        var test = tendeuseBusiness.toRight(tendeuse);

        // Then
        assertEquals(Direction.E, test.getDirectionFinale());

    }

    @Test
    @DisplayName("testToLeft : doit retourner un objet tendeuse avec une nouvelle direction")
    void testToLeft() {

        var tendeuse = TeudeuseSample.TONDEUSE_3;
        // When
        var test = tendeuseBusiness.toRight(tendeuse);

        // Then
        assertEquals(Direction.S, test.getDirectionFinale());

    }


}
