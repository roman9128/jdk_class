package client_server.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class LogMaster implements Repository {

    private static final String LOG_FILE = "log.txt";

    public LogMaster() {
    }

    @Override
    public void writeToLog(String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true)) {
            fileWriter.write(text);
        } 
    }

    @Override
    public String sendLog() throws FileNotFoundException, IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            return builder.toString();
        }
    }
}