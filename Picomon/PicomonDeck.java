import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class PicomonDeck {

    private PicomonCard[] cards;
    
    public PicomonDeck() {
        // Note how the default deck thus has 10 cards.
        this(new PicomonCard[] {
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard(),
            new PicomonCard()
        });
    }

    public PicomonDeck(PicomonCard... cards) {
        this.cards = cards;     
    }

    public PicomonCard cardAt(int index) {
        if (index < 0 || index > cards.length - 1) {
            throw new IllegalArgumentException();
        } else {
            return cards[index];
        }
    }

    public int getSize() {
        return cards.length;
    }

    public void shuffle() {
        // Implement me!
        Random rand = new Random();
        for(int i = getSize() - 1; i > 0; i--){
            int index = rand.nextInt(i + 1);
            PicomonCard temp = cards[index];
            cards[index] = cards [i];
            cards[i] = temp;
        }

    }

    public boolean orderedEquals(PicomonDeck other) {
        // Implement me!
        return true;
    }

    @Override
    public String toString() {
        String result = "[\n";
        for (PicomonCard card: cards) {
            result += "    ";
            result += card;
            result += "\n";
        }
        return result + "]";
    }

    // Advanced Java---study with caution.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        PicomonDeck other = (PicomonDeck)obj;
        // Due to the possibility of duplicates, deck comparison is a notch trickier.
        // Our approach is to count the cards in each deck then ensure that the cards
        // and counts are the same.
        return tally().equals(other.tally());
    }

    private Map<PicomonCard, Integer> tally() {
        Map<PicomonCard, Integer> result = new HashMap<PicomonCard, Integer>();
        for (PicomonCard card: cards) {
            Integer value = result.get(card);
            if (value == null) {
                result.put(card, 1);
            } else {
                result.put(card, value + 1);
            }
        }
        
        return result;
    }
}
