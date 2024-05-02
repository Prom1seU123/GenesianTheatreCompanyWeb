package com.au.GenesianTheatreCompany.service.impl;

import com.au.GenesianTheatreCompany.service.LoggingService;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoggingServiceImpl implements LoggingService{

    private static final String LOG_FILE_PATH = "caplogfile.log";

    public void writeLog(String message) {
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fileWriter.write(timestamp + " - " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
