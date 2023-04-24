import java.io.*;
import java.net.*;
import java.util.List;

public class Client {

    private String name;
    private List<Card> hand;

    public Client(String name, List<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Send message to server
        String message = "Hello from client";
        out.println(message);

        // Receive message from server
        String response = in.readLine();
        System.out.println("Received message from server: " + response);

        socket.close();
    }
}
