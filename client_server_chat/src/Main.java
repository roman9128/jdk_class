import client_server.client.ClientGUI;
import client_server.client.ClientLogic;
import client_server.client.Connectable;
import client_server.server.ServerGUI;
import client_server.server.ServerLogic;

public class Main {
    public static void main(String[] args) throws Exception {
        
        ServerLogic server = new ServerLogic();
        server.setView(new ServerGUI(server));

        Connectable client1 = new ClientLogic();
        client1.setView(new ClientGUI(client1));
        client1.setServer(server);
        
        Connectable client2 = new ClientLogic();
        client2.setView(new ClientGUI(client2));
        client2.setServer(server);

        Connectable client3 = new ClientLogic();
        client3.setView(new ClientGUI(client3));
        client3.setServer(server);

    }
}
