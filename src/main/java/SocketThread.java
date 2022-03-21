import com.google.gson.Gson;

import javax.mail.MessagingException;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SocketThread extends Thread{

    public SocketThread(Socket client) throws IOException, MessagingException {
        String client_address = (client.getInetAddress()).toString().replace("/", " ");
        System.out.println("Client" + client_address + " has connected!");
        DataOutputStream output = new DataOutputStream(client.getOutputStream());
        output.writeUTF("Welcome" + client_address + "! You registration request in processing!");
        output.flush();
        byte[] buffer = new byte[8192];
        DataInputStream inputStream = new DataInputStream(client.getInputStream());
        inputStream.read(buffer);
        Gson gson = new Gson();
        User user = gson.fromJson(new String(buffer,"UTF8").replace("\u0000",""), User.class);
        MailSender.Send(user);
    }
}
