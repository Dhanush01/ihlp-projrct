import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<Card> deck;
    private static ServerSocket serverSocket;

    Server() throws IOException {
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started.");
        deck = generateCards();
        System.out.println("Server Side Deck" + deck);
    }

    private List<Card> generateCards() {
        List<Card> cards = Arrays.asList(
                new Card("Spade", "Ace", 1),
                new Card("Spade", "2", 2),
                new Card("Spade", "3", 3),
                new Card("Spade", "4", 4),
                new Card("Spade", "5", 5),
                new Card("Spade", "6", 6),
                new Card("Spade", "7", 7),
                new Card("Spade", "8", 8),
                new Card("Spade", "9", 9),
                new Card("Spade", "10", 10),
                new Card("Spade", "Jack", 11),
                new Card("Spade", "Queen", 12),
                new Card("Spade", "King", 13)
        );
        Collections.shuffle(cards);
        return cards;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run(server);


    }
    public void run(Server server) throws IOException {

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message from client: " + inputLine);
                out.println("Server received message: " + inputLine);
            }
//            for(int i =0; i<=13 ; i++){
//                Card card = server.pickCard();
//                System.out.println("Picked Card"+ card);
//            }
        }
    }

    public Card pickCard() {
        if (deck.isEmpty()) {
            return null; // no cards left in the server's suit
        }

        // pick a random card from the remaining cards in the suit
        int randomIndex = new Random().nextInt(deck.size());
        Card pickedCard = deck.get(randomIndex);

        // remove the picked card from the server's suit
        deck.remove(randomIndex);

        return pickedCard;
    }


}

