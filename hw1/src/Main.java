import client_server.client.Client;
import client_server.server.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        new Client(server);
        new Client(server);        
    }
}
