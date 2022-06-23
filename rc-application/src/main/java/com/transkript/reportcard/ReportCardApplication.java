package com.transkript.reportcard;

import com.transkript.reportcard.config.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@SpringBootApplication
public class ReportCardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportCardApplication.class, args);
        prepareWorkingDir();
    }

    private static void prepareWorkingDir() {
        File settingsFile = new File(AppConstants.settingsFile);
        if(!settingsFile.exists()) {
            try {
                Files.createFile(Path.of(settingsFile.getAbsolutePath()));
                log.info("Settings file created");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } else {
            log.info("Settings file already exists");
        }
    }
}
