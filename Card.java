class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
    public static Card fromString(String cardString) {
        String[] parts = cardString.split(" ");
        String suit = parts[0];
        String rank = parts[1];
        int value = Integer.parseInt(parts[2]);
        return new Card(suit, rank, value);
    }
}
