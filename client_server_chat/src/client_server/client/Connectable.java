package client_server.client;

import client_server.server.ServerLogic;

public interface Connectable {

    void login();

    void receiveMsg(String text);

    void setView(ClientView view);

    void setServer(ServerLogic server);

}