package client_server.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Repository {

    void writeToLog(String text) throws IOException;

    String sendLog() throws FileNotFoundException, IOException;

}
