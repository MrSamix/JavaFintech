package org.example.runapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class InitApplication implements CommandLineRunner  {
    @Value("${upload.dir}")
    private String uploadDir;


    @Override
    public void run(String... args) throws Exception {
        // make forder, if folder not found
        Files.createDirectories(Paths.get(uploadDir));
    }
}
