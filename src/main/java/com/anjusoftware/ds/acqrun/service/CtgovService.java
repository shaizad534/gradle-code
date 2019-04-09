package com.anjusoftware.ds.acqrun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CtgovService {
    private final String SRC_URL = "https://clinicaltrials.gov/search?resultsxml=true";
    private final String OUTPUT_FILENAME = "ctgov.zip";

    private Logger logger = LoggerFactory.getLogger(CtgovService.class);

    public File retrieveFile() {
        logger.info("Retrieving ctgov file");
        try (InputStream in = URI.create(SRC_URL).toURL().openStream()) {
            Path out = Paths.get("./ctgov.zip");
            Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error(e.toString());
            logger.error("{}", e.getStackTrace());
            logger.error("{}", e.getMessage());
        }

        File file = new File(OUTPUT_FILENAME);
        logger.info("Returned a file of size {}", file.length());
        return file;
    }
}
