import client_server.client.ClientGUI;
import client_server.client.ClientLogic;
import client_server.server.ServerGUI;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerGUI server = new ServerGUI();
        new ClientLogic(server);
        new ClientLogic(server);
        // ClientGUI client1 = new ClientGUI();
        // ClientGUI client2 = new ClientGUI();

    }
}
