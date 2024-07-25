package com.tendeuse_automatique.batch.readers;

import com.tendeuse_automatique.dto.TondeuseInDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * File TXT Reader
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class TondeuseReader implements ItemReader<TondeuseInDto> {


    private final ResourceLoader resourceLoader;

    @Value("${spring.batch.chunkSize}")
    private int chunkSize;

    @Value("${input.file.path}")
    private String inputFilePath;

    private BufferedReader reader;
    private String surface;
    private Queue<String> linesQueue;


    @PostConstruct
    private void initializeReader() throws IOException {
        Resource resource = new ClassPathResource(inputFilePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            this.reader = br;
            this.linesQueue = new LinkedList<>();
            readNextChunk();
        } catch (IOException e) {
            log.error("Failed to initialize reader", e);
            throw e;
        }
    }

    /**
     * methode pour parcourir le fichier par chunk et ajouter les elemnt dans la liste (linesQueue)
     *
     */
    private void readNextChunk() throws IOException {
        String line;
        int count = 0;
        while (count < chunkSize && (line = reader.readLine()) != null) {
            linesQueue.add(line);
            count++;
        }
    }

    @Override
    public TondeuseInDto read() throws Exception {
        if (linesQueue == null || linesQueue.isEmpty()) {
            return null;
        }

        if (surface == null) {
            surface = linesQueue.poll();
        }

        String positionInitiale = null;
        String instruction = null;

        if (!linesQueue.isEmpty()) {
            positionInitiale = linesQueue.poll();
        }

        if (!linesQueue.isEmpty()) {
            instruction = linesQueue.poll();
        }

        if (positionInitiale == null || instruction == null) {
            return null;
        }

        return TondeuseInDto.builder()
                .surface(surface)
                .positionInitiale(positionInitiale)
                .instructions(instruction)
                .build();
    }
}
