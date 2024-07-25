package com.tondeuse_automatique.test.service;

import com.tendeuse_automatique.business.ITondeuseBusiness;
import com.tendeuse_automatique.entity.Tondeuse;
import com.tendeuse_automatique.service.impl.ITondeuseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@ExtendWith({MockitoExtension.class})
public class TondeuseServiceTest {


    @InjectMocks
    private ITondeuseServiceImpl tondeuseService;

    @Mock
    private ITondeuseBusiness tendeuseBusiness;


    private final String outputRepoPath = "test/output/repo";

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(tondeuseService, "outputRepoPath", outputRepoPath);
    }

    @Test
    public void testWriteToFile_success() throws Exception {
        // Arrange
        Tondeuse tondeuse = new Tondeuse();
        // Set up your tondeuse object as needed
        List<Tondeuse> tondeuses = Collections.singletonList(tondeuse);

        // Act
        tondeuseService.writeToFile(tondeuses);

        // Assert
        Path directory = Paths.get(outputRepoPath);
        Assertions.assertTrue(Files.exists(directory));


        File dir = new File(outputRepoPath);
        File[] files = dir.listFiles((d, name) -> name.startsWith("Tondeuse_output_") && name.endsWith(".txt"));
        Assertions.assertNotNull(files);
        Assertions.assertTrue(files.length > 0);
    }


}
