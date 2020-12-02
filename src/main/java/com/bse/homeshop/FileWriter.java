package com.bse.homeshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter implements Writer {
    private String fileName;
    private String content;
    private Path path;

    public FileWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void start() {
        path = Paths.get(fileName);
        content = "";
    }

    @Override
    public void writeLine(String line) {
        content += line + "%n";
    }

    @Override
    public void stop() {
        try {
            Files.write(path, String.format(content).getBytes());
        } catch (IOException e) {
            System.err.println("Impossible d'enregistrer la facture : " + e);
        }
    }
}
