package client_server.server;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import client_server.client.Connectable;
import client_server.repository.LogMaster;
import client_server.repository.Repository;

public class ServerLogic {

    private ServerView view;
    private boolean isWorking;
    private Repository repository;
    private ArrayList<Connectable> connectedUsers;

    public ServerLogic() {
        repository = new LogMaster();
        connectedUsers = new ArrayList<>();
    }

    public void setView(ServerView view) {
        this.view = view;
    }

    public void start() {
        if (!isWorking) {
            isWorking = true;
            String startMsg = System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - server starts";
            writeToLog(startMsg);
            view.start(startMsg);

        }
    }

    public void stop() {
        if (isWorking) {
            isWorking = false;
            String stopMsg = System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - server stopped";
            writeToLog(stopMsg);
            view.stop(stopMsg);
            sendNewTextToEveryone(stopMsg);
            while (!connectedUsers.isEmpty()) {
                connectedUsers.getLast().login();
            }
        }
    }

    public void writeToLog(String text) {
        try {
            repository.writeToLog(text);
        } catch (IOException e) {
            view.showText(e.getMessage());
        }
    }

    public String sendLog() {
        if (isWorking) {
            try {
                return repository.sendLog();
            } catch (FileNotFoundException e) {
                view.showText(e.getMessage());
            } catch (IOException e) {
                view.showText(e.getMessage());
            }
        }
        return "";
    }

    public void addNewUser(Connectable connectable) {
        connectedUsers.add(connectable);
    }

    public void removeUser(Connectable connectable) {
        connectedUsers.remove(connectable);
    }

    public void sendNewTextToEveryone(String text) {
        for (Connectable connectable : connectedUsers) {
            connectable.receiveMsg(text);
        }
    }

    public void takeMsg(String message) {
        if (isWorking) {
            view.showText(message);
            writeToLog(message);
            sendNewTextToEveryone(message);
        }
    }

    public boolean checkPassword(String pass) {
        String okPass = "password";
        return okPass.equals(pass);
    }

    public boolean isWorking() {
        return isWorking;
    }
}