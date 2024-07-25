package com.tondeuse_automatique.test.service;

import com.tendeuse_automatique.business.ITondeuseBusiness;
import com.tendeuse_automatique.service.impl.ITondeuseServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@ExtendWith({MockitoExtension.class})
public class TondeuseServiceTest {


    @InjectMocks
    private ITondeuseServiceImpl tendeuseService;

    @Mock
    private ITondeuseBusiness tendeuseBusiness;






}
