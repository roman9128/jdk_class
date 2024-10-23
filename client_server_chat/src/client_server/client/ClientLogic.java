package client_server.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import client_server.server.ServerLogic;

public class ClientLogic implements Connectable {

    private boolean isConnected;
    private ServerLogic server;
    private ClientView view;

    public ClientLogic() {
    }

    @Override
    public void setView(ClientView view) {
        this.view = view;
    }

    @Override
    public void setServer(ServerLogic server) {
        this.server = server;
    }

    @Override
    public void login() {

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
                view.receiveMsg("\nwrong password");
            }
        } else if (!server.isWorking() && !isConnected) {
            view.receiveMsg("\nserver is offline, try later");
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
        view.userDisconnected();
    }

    @Override
    public void sendMsg(String message) {
        if (server.isWorking() && isConnected) {
            server.takeMsg(System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - " + view.getLogin() + " wrote: " + message);

            view.messageSent();
            server.sendNewTextToEveryone(message);
        }
    }

    @Override
    public void receiveMsg(String message) {
        view.receiveMsg(message);
    }

    @Override
    public void checkServer() {
        if (server.isWorking()) {
            view.receiveMsg("\nserver is online, connection is possible");
        } else {
            view.receiveMsg("\nserver is offline, try later");
        }
    }
}