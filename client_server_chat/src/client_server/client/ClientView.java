package client_server.client;

public interface ClientView {

    String getLogin();

    String getPassword();

    void sendMsg();

    void messageSent();

    void receiveMsg(String message);

    void userConnected(String loginMsg);

    void userDisconnected();

}