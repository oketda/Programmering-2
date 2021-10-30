import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {

    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> newDeck = new ArrayList<>();

    public boolean assign(int n){
        newDeck.add(deck.get(n-1));
        return true;
    }

    public static void main(String[] args) {
        Deck obj = new Deck();
        Card card = new Card('S', 1);
        Random random = new Random();

        char suit = 'S';
        for (int i = 0; i < 4; i++) {
            if (i == 1){
                suit = 'H';
            }
            if (i == 2){
                suit = 'D';
            }
            if (i == 3){
                suit = 'C';
            }
            for (int j = 0; j < 13; j++) {
                obj.deck.add(new Card(suit, j+1));
            }
        }

        int numberOfExcecutions = random.nextInt(52)+1;
        int[] allreadyDrawn = new int[numberOfExcecutions];
        int justDrawed = 0;
        for (int i = 0; i < numberOfExcecutions; i++) {
            boolean run = true;
            while (run) {
                int n = random.nextInt(52) + 1;
                for (int j = 0; j < allreadyDrawn.length; j++) {
                    if (allreadyDrawn[j] == n) {
                        j = allreadyDrawn.length;
                    } else if (j == allreadyDrawn.length - 1 && n != allreadyDrawn[j]) {
                        obj.assign(n);
                        allreadyDrawn[justDrawed] = n;
                        justDrawed++;
                        run = false;
                    }
                }
            }
        }


        obj.newDeck.stream().filter(c -> c.getSuit() == 'S').forEach(s -> System.out.print(s+ " "));
        System.out.println();

        System.out.println(obj.newDeck.stream().filter(c -> c.getSuit() == 'H').collect(Collectors.toList()));
        System.out.println();

        //farge
        System.out.println(obj.newDeck.stream().map(c -> c.getSuit() == 'S' || c.getSuit() == 'C' ? 'B':'R').collect(Collectors.toList()));
        System.out.println();

        //total
        System.out.println(obj.newDeck.stream().map(Card::getFace).reduce((a, b) -> a + b).get());
        System.out.println();

        System.out.println("Queen of spades?");
        System.out.println(obj.newDeck.stream().anyMatch(c -> c.getSuit() == 'S' && c.getFace() == 12));
        System.out.println();

        System.out.println("Flush?");
        boolean flush = false;
        if (obj.newDeck.size() == 5) {
            if (obj.newDeck.stream().allMatch(c -> c.getSuit() == 'S')) {
                flush = true;
            }
            else if (obj.newDeck.stream().allMatch(c -> c.getSuit() == 'H')) {
                flush = true;
            }
            else if (obj.newDeck.stream().allMatch(c -> c.getSuit() == 'D')) {
                flush = true;
            }
            else if (obj.newDeck.stream().allMatch(c -> c.getSuit() == 'C')) {
                flush = true;
            }
        }
        System.out.println(flush);



    }
}
