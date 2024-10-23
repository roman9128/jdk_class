package client_server.server;

public interface ServerView {

    void start(String startMsg);

    void stop(String stopMsg);

    void showText(String message);

}