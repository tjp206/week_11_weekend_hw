import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Play TJ's BlackJack game at your own risk!");
        System.out.println("Remember - When the fun stops, stop.");

        Deck playingDeck= new Deck();
        playingDeck.createNewDeck();
        playingDeck.shuffleCards();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        double playerCash = 100.00;
        Scanner playerInput = new Scanner(System.in);

        while(playerCash > 0) {
            System.out.println("You have £" + playerCash + ", what is your stake?");
            double playerBet = playerInput.nextDouble();
            if(playerBet > playerCash) {
                System.out.println("You can't stake more than what you already have!");
                break;
            }

            boolean endRound = false;

            playerDeck.drawCard(playingDeck);
            playerDeck.drawCard(playingDeck);

            dealerDeck.drawCard(playingDeck);
            dealerDeck.drawCard(playingDeck);

            while(true) {
                System.out.println("Your hand is:");
                System.out.println(playerDeck.toString());
                System.out.println("Your current hand is worth: " + playerDeck.totalCardsValue());

                System.out.println("Dealers hand is: " + dealerDeck.getCard(0).toString() + " and [Hole Card]");
                System.out.println("Do you want to Twist(1) or Stick(2)?");
                int playerResponse = playerInput.nextInt();
                if(playerResponse == 1) {
                    playerDeck.drawCard(playingDeck);
                    System.out.println("Your card is: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    if(playerDeck.totalCardsValue() > 21) {
                        System.out.println("That's a bust! You had " + playerDeck.totalCardsValue());
                        playerCash -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if(playerResponse == 2) {
                    break;
                }
            }

            System.out.println("Dealers hand is: " + dealerDeck.toString());
            if((dealerDeck.totalCardsValue() >= 17) && (dealerDeck.totalCardsValue() > playerDeck.totalCardsValue()) && endRound == false) {
                System.out.println("Dealer wins!");
                playerCash -= playerBet;
                endRound = true;
            }

            while(dealerDeck.totalCardsValue() < 17 && endRound == false) {
                dealerDeck.drawCard(playingDeck);
                System.out.println("Dealer drew a: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            System.out.println("Dealers hand is worth: " + dealerDeck.totalCardsValue());

            if(dealerDeck.totalCardsValue() > 21 && endRound == false) {
                System.out.println("Dealer has gone bust - you win!");
                playerCash += playerBet;
                endRound = true;
            }

            if(playerDeck.totalCardsValue() == dealerDeck.totalCardsValue() && endRound == false) {
                System.out.println("It's a draw.");
                endRound = true;
            }

            if(playerDeck.totalCardsValue() > dealerDeck.totalCardsValue() && endRound == false) {
                System.out.println("You won this round!");
                playerCash += playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of the hand.");

        }
        System.out.println("Ouch, you ran out of cash & maybe it's time to seek help for your gambling problem. Better luck next time!");
    }
}
