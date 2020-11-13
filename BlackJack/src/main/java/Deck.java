import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void createNewDeck() {
        for(Suit cardSuit : Suit.values()) {
            for(Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffleCards() {
        ArrayList<Card> firstDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCard = 0;
        int getDeckSize = this.cards.size();
        for(int card = 0; card < getDeckSize; card++) {
            randomCard = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
            firstDeck.add(this.cards.get(randomCard));
            this.cards.remove(randomCard);
        }
        this.cards = firstDeck;
    }

    public String toString() {
        String cardList = "";
        for(Card chosenCard : this.cards) {
            cardList += "\n" + chosenCard.toString();
        }
        return cardList;
    }

    public Card getCard(int card) {
        return this.cards.get(card);
    }

    public void removeCard(int card) {
        this.cards.remove(card);
    }

    public void addCard(Card newCard) {
        this.cards.add(newCard);
    }

    public void drawCard(Deck firstDeck) {  // Move cards from original deck to new deck
        this.cards.add(firstDeck.getCard(0));
        firstDeck.removeCard(0);
    }

    public int deckSize() {
        return  this.cards.size();
    }

    public void moveAllToDeck(Deck movedCards) {
        int thisDeck = this.cards.size();
        for(int i = 0; i < thisDeck; i++) {
            movedCards.addCard(this.getCard(i));
        }
        for(int i = 0; i < thisDeck; i++) {
            this.removeCard(0);
        }
    }

    public int totalCardsValue() {
        int totalValue = 0;
        int aces = 0;
        for(Card card : this.cards) {
            switch(card.getValue()) {
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: totalValue += 1; break;
            }
        }
        for(int i = 0; i < aces; i++) {
            if(totalValue > 10) {
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }
        }
        return totalValue;
    }

}
