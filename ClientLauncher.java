import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientLauncher {
    public Socket socket;
    BufferedReader in;
    PrintWriter out ;
    List<Client> clients;

    ClientLauncher(){
        clientInitialisation();
    }

    void clientInitialisation(){
        socket = null;
        in = null;
        out = null;
        try {
            Socket socket = new Socket("localhost", 5000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String[] messages = {"Hello from client 1", "Hello from client 2", "Hello from client 3"};

        List<Client> clients = generateClients();

        for (Client client : clients) {
            final String message = client.getName();

            new Thread(() -> {
                try {
                    Socket socket = new Socket("localhost", 5000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    out.println("Client " + (client.getName()) + ": " + client.getHand());

                    String response = in.readLine();
                    System.out.println(response);

                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static List<Client> generateClients() {
        Client client1 = new Client("Client 1", Arrays.asList(
                new Card("Hearts", "Ace", 1),
                new Card("Hearts", "2", 2),
                new Card("Hearts", "3", 3),
                new Card("Hearts", "4", 4),
                new Card("Hearts", "5", 5),
                new Card("Hearts", "6", 6),
                new Card("Hearts", "7", 7),
                new Card("Hearts", "8", 8),
                new Card("Hearts", "9", 9),
                new Card("Hearts", "10", 10),
                new Card("Hearts", "Jack", 11),
                new Card("Hearts", "Queen", 12),
                new Card("Hearts", "King", 13)
        ));

        Client client2 = new Client("Client 2", Arrays.asList(
                new Card("Diamonds", "Ace", 1),
                new Card("Diamonds", "2", 2),
                new Card("Diamonds", "3", 3),
                new Card("Diamonds", "4", 4),
                new Card("Diamonds", "5", 5),
                new Card("Diamonds", "6", 6),
                new Card("Diamonds", "7", 7),
                new Card("Diamonds", "8", 8),
                new Card("Diamonds", "9", 9),
                new Card("Diamonds", "10", 10),
                new Card("Diamonds", "Jack", 11),
                new Card("Diamonds", "Queen", 12),
                new Card("Diamonds", "King", 13)
        ));

        Client client3 = new Client("Client 3", Arrays.asList(
                new Card("Clubs", "Ace", 1),
                new Card("Clubs", "2", 2),
                new Card("Clubs", "3", 3),
                new Card("Clubs", "4", 4),
                new Card("Clubs", "5", 5),
                new Card("Clubs", "6", 6),
                new Card("Clubs", "7", 7),
                new Card("Clubs", "8", 8),
                new Card("Clubs", "9", 9),
                new Card("Clubs", "10", 10),
                new Card("Clubs", "Jack", 11),
                new Card("Clubs", "Queen", 12),
                new Card("Clubs", "King", 13)
        ));

        List<Client> clients = new ArrayList<>(3);
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        return clients;
    }

    public void sendCard(Card card) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Convert Card object to string
        String cardString = card.toString();
        // Send card to server
        out.println(cardString);
        out.flush();
    }

    public Card receiveCard() throws IOException {
        // Wait for incoming message from server
        String cardString = in.readLine();
        // Parse string to Card object
        Card card = Card.fromString(cardString);
        return card;
    }
}
