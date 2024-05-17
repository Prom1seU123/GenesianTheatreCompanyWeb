package com.au.GenesianTheatreCompany.ServieceImplTest;

import com.au.GenesianTheatreCompany.service.impl.LoggingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggingServiceImplTest {

    private LoggingServiceImpl loggingService;
    private static final String LOG_FILE_PATH = "caplogfile.log";

    @BeforeEach
    void setUp() {
        // Ensure we are using a test file
        System.setProperty("user.dir", System.getProperty("java.io.tmpdir"));
        loggingService = new LoggingServiceImpl();
    }

    @Test
    void testWriteLog() throws Exception {
        String testMessage = "Test log message";
        loggingService.writeLog(testMessage);

        // Read the last line from log file
        File logFile = new File(LOG_FILE_PATH);
        assertTrue(logFile.exists());

        String lastLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }
        }

        // Check if the last line contains the test message
        assertTrue(lastLine.contains(testMessage));
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up the test log file
        Files.deleteIfExists(Paths.get(LOG_FILE_PATH));
    }
}
