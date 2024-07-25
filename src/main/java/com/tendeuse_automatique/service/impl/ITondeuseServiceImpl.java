package com.tendeuse_automatique.service.impl;

import com.tendeuse_automatique.business.ITondeuseBusiness;
import com.tendeuse_automatique.constants.ErrorConstants;
import com.tendeuse_automatique.entity.Tondeuse;
import com.tendeuse_automatique.exception.TondeuseException;
import com.tendeuse_automatique.service.ITondeuseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ITondeuseServiceImpl implements ITondeuseService {

    private final ITondeuseBusiness tondeuseBusiness;

    @Value("${output.repo.path}")
    private String outputRepoPath;
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    public static final String EXTENSION_TXT = ".txt";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);


    @Override
    public void writeToFile(List<Tondeuse> tendeuses) {

        Path directory = Paths.get(outputRepoPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                log.error("Erreur lors de la création du répertoire: {}" , e.getMessage());
                throw TondeuseException.from("Erreur lors de la création du répertoire: " + e.getMessage());
            }
        }

        if(CollectionUtils.isEmpty(tendeuses)){
            throw TondeuseException.from(ErrorConstants.TE_1000);
        }

        // Générer un nom de fichier unique basé sur la date et l'heure actuelle

        String fileName = "Tondeuse_output_" + LocalDateTime.now().format(formatter) + EXTENSION_TXT;
        Path filePath = directory.resolve(fileName);

        // Créer un nouveau fichier et écrire dedans
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Tondeuse tondeuse : tendeuses) {
                tondeuseBusiness.executeInstructions(tondeuse);
                writer.write(getFormatedTexte(tondeuse));
                writer.newLine();
            }
        } catch (IOException e) {
            throw TondeuseException.from("Erreur lors de l'écriture dans le fichier: " + e.getMessage());
        }
    }

    private static String getFormatedTexte(Tondeuse tondeuse) {
        return "positionInitiale : " + tondeuse.getPositionInitiale() + " ------- " +
                "instructions: " + tondeuse.getInstructions() + " ------- " +
                "positionFinale: " + tondeuse.getPositionFinale();
    }


}





