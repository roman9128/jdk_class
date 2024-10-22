package client_server.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import client_server.server.ServerGUI;
import client_server.server.ServerLogic;

public class ClientLogic {

    private boolean isConnected;
    // private ServerLogic server;
    private ServerGUI server;
    private ClientView view;

    public ClientLogic(ServerGUI server) {
        this.view = new ClientGUI(this);
        this.server = server;
    }

    public void loginLogout() {

        if (server.isWorking() && !isConnected) {
            if (server.checkPassword(view.getPassword())) {
                StringBuilder builder = new StringBuilder();
                String loginMsg = System.lineSeparator()
                        + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                        + " - " + view.getLogin() + " connected";

                builder.append(System.lineSeparator());
                builder.append(server.sendLog());
                builder.append(loginMsg);

                isConnected = true;
                server.takeMsg(loginMsg);
                server.addNewUser(this);
                view.userConnected(builder.toString());

            } else {
                view.showText("\nwrong password");
            }
        } else if (!server.isWorking()) {
            view.showText("\nserver is offline, try later");
        } else if (isConnected) {
            logout();
        }
    }

    private void logout() {
        String logoutMsg = System.lineSeparator()
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + " - "
                + view.getLogin() + " disconnected";
        isConnected = false;
        server.takeMsg(logoutMsg);
        server.removeUser(this);
        view.userDisconnected(logoutMsg);
    }

    public void sendMsg(String message) {
        if (server.isWorking() && isConnected) {
            server.takeMsg(System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - " + view.getLogin() + " wrote: " + message);

            view.messageSent();
            server.sendNewTextToEveryone();
        }
    }

    public void receiveMsg(String message) {
        view.receiveMsg(message);
    }

    public void checkServer() {
        if (server.isWorking()) {
            view.showText("\nserver is online, connection is possible");
        } else {
            view.showText("\nserver is offline, try later");
        }
    }

}