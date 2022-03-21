import javax.mail.MessagingException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, MessagingException {
        ServerSocket server = new ServerSocket(6967);
        System.out.println("Server has started!");

        ArrayList<Socket> clients = new ArrayList<Socket>();
        while (true) {
            Socket new_client = server.accept();
            clients.add(new_client);
            for (Socket client : clients) {
                SocketThread thread = new SocketThread(client);
                thread.start();
            }
        }
    }
}
