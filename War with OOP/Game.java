package WarOOP;

import java.util.ArrayList;
import java.util.Collections;

public class Game
{

	public static void main(String[] args)
	{
		// Initialize a new Deck and shuffle it
		Deck deck = new Deck();
		deck.initializeDeck();
		deck.shuffle();

		// Had to convert the Deck to an array list of cards in order to iterate through
		// it
		ArrayList<Card> cards = new ArrayList<>();
		cards = deck.toCardsList();

		// Initialize the two game players and the winner object which we assign upon a
		// win
		Player playerOne = new Player("Player One");
		Player playerTwo = new Player("Player Two");
		Player winner = new Player();

		// Set the hands of the two players initially by calling the deal function which
		// removes each element from
		// the array list up to the count that is inputed
		playerOne.setHand(deal(cards, cards.size() / 2));
		playerTwo.setHand(deal(cards, cards.size()));

		// call startGame with our two player objects which now have a hand assigned to
		// them
		// startGame returns a Player object which we assign to be the winner of the
		// game
		winner = startGame(playerOne, playerTwo, winner);

		// Simply prints out the winners name or stalemate in the event of a draw
		System.out.printf("%s Wins!", winner.getPlayerName());

	}

	/**
	 * This method takes in an array list and returns an array list of the specified
	 * size
	 * 
	 * @param cards - an array list of cards
	 * @param size  - the size of the hand you want to produce
	 * @return ArrayList - typeof Card
	 */
	public static ArrayList<Card> deal(ArrayList<Card> cards, int size)
	{
		ArrayList<Card> hand = new ArrayList<>();

		while (hand.size() < size)
		{
			hand.add(cards.remove(0));
		}

		return hand;
	}

	/**
	 * THis method starts up the game and calls other methods as necessary in order
	 * to complete the task
	 * 
	 * @param playerOne - an object of type Player
	 * @param playerTwo - an object of type Player
	 * @param winner
	 * @return Player
	 */
	public static Player startGame(Player playerOne, Player playerTwo, Player winner)
	{

		// Initialize the wardCards variable in order to store the cards when the
		// trigger for "war" happens
		ArrayList<Card> warCards = new ArrayList<>();

		while (playerOne.isCardsLeft() || playerTwo.isCardsLeft())
		{

			// I call the checkPlayerDecks method in order to see if a player has run out of
			// cards and therefore lost
			winner = checkPlayerDecks(playerOne, playerTwo, winner);

			if (winner.equals(playerOne) || winner.equals(playerTwo))
			{
				return winner;
			}
			else
			{
				// If there hasn't been a winner declared yet, I call the faceOff function to
				// compare the top cards
				// of each players hand to see who wins that faceOff
				faceOff(playerOne, playerTwo, warCards, winner);

			}
		}

		return winner;
	}

	/**
	 * This method compares the top cards from each hand which returns 1, 0, or -1
	 * If it is 1, then player one won the turn and the cards go to their discard
	 * pile 0 then we go to war! (a feature I implemented based on my knowledge of
	 * how the game works) If it is -1 then player two won the turn and the cards go
	 * to their discard pile
	 * 
	 * @param playerOne - an object of type Player
	 * @param playerTwo - an object of type Player
	 * @param warCards  - an array list of type Card
	 * @param winner    - an object of type Player
	 */
	public static void faceOff(Player playerOne, Player playerTwo, ArrayList<Card> warCards, Player winner)
	{
		// Getting the top card from each hand
		Card pOneCard = playerOne.getHand().remove(0);
		Card pTwoCard = playerTwo.getHand().remove(0);

		// Comparing the two card's values
		int value = Integer.compare(pOneCard.getFaceValue(), pTwoCard.getFaceValue());
		System.out.printf("\"%s\" vs \"%s\" - \"%s Wins The Turn\"\n", pOneCard, pTwoCard,
				value == 1 ? playerOne.getPlayerName() : playerTwo.getPlayerName());
		// Player one wins the turn
		if (value == 1)
		{
			// if they went to war and this was the turn to end the war add the war cards
			// won to the discard pile
			if (warCards.size() > 0)
			{
				for (Card card : warCards)
				{
					playerOne.getDiscard().add(card);
				}
				warCards.clear();
			}
			// add the current turn cards to the discard pile
			playerOne.getDiscard().add(pOneCard);
			playerOne.getDiscard().add(pTwoCard);
		}
		else if (value == 0)
		{
			// This calls the function war, it is a feature I implemented to better reflect
			// how the game is played
			warCards = war(playerOne, playerTwo, warCards, winner);
		}
		// player two wins the turn
		else
		{
			// if they went to war and this was the turn to end the war add the war cards
			// won to the discard pile
			if (warCards.size() > 0)
			{
				for (Card card : warCards)
				{
					playerTwo.getDiscard().add(card);
				}
				warCards.clear();
			}
			// add the current turn cards to the discard pile
			playerTwo.getDiscard().add(pOneCard);
			playerTwo.getDiscard().add(pTwoCard);

		}
	}

	/**
	 * This method checks the player hand and discard pile to see if they are empty
	 * and they have lost Then if they main hand is empty and the discard pile isn't
	 * we load the discard pile into the main hand and continue playing after
	 * shuffling the cards. I found if you don't shuffle it will cause the game to
	 * go into a stalemate
	 * 
	 * @param playerOne - an object of type Player
	 * @param playerTwo - an object of type Player
	 * @param winner    - an object of type Player
	 * @return an object of type Player
	 */
	public static Player checkPlayerDecks(Player playerOne, Player playerTwo, Player winner)
	{

		if (playerOne.getHand().size() == 0)
		{
			if (playerOne.getDiscard().size() == 0)
			{
				playerOne.setHasCardsLeft(false);
				winner = playerTwo;
			}
			else
			{
				loadHandFromDiscard(playerOne);
				Collections.shuffle(playerOne.getHand());
			}
		}
		if (playerTwo.getHand().size() == 0)
		{
			if (playerTwo.getDiscard().size() == 0)
			{
				playerTwo.setHasCardsLeft(false);
				winner = playerOne;
			}
			else
			{
				loadHandFromDiscard(playerTwo);
				Collections.shuffle(playerTwo.getHand());
			}
		}
		return winner;
	}

	/**
	 * This is where we go to war. How war works is if the two card's values are the
	 * same you put down two more cards then a third final card in which you compare
	 * against their third card. also, if they do not have enough cards to go to
	 * war, they lose the game
	 * 
	 * @param playerOne - an object of type Player
	 * @param playerTwo - an object of type Player
	 * @param warCards  - an ArrayList of type Card
	 * @param winner    - an object of type Player
	 * @return ArrayList of type Card
	 */
	public static ArrayList<Card> war(Player playerOne, Player playerTwo, ArrayList<Card> warCards, Player winner)
	{
		// If hand size isn't large enough we load in the discard pile first before
		// playing the cards
		if (playerOne.getHand().size() < 2)
		{
			// If you don't have enough cards to play war, you lose
			if (playerOne.getDiscard().size() + playerOne.getHand().size() <= 2)
			{
				winner = playerTwo;
				return warCards;
			}
			else
			{
				for (Card card : playerOne.getHand())
				{
					playerOne.getDiscard().add(card);
				}
				loadHandFromDiscard(playerOne);
			}
		}
		// If hand size isn't large enough we load in the discard pile first before
		// playing the cards
		if (playerTwo.getHand().size() < 2)
		{
			// If you don't have enough cards to play war, you lose
			if (playerTwo.getDiscard().size() + playerTwo.getHand().size() <= 2)
			{
				winner = playerOne;
				return warCards;
			}
			else
			{
				for (Card card : playerTwo.getHand())
				{
					playerTwo.getDiscard().add(card);
				}
				loadHandFromDiscard(playerTwo);
			}
		}
		// Here we are adding the 'throw away' cards to the warCards array list
		for (int i = 0; i < 2; i++)
		{
			warCards.add(playerOne.getHand().remove(0));
			warCards.add(playerTwo.getHand().remove(0));
		}

		return warCards;

	}

	/**
	 * This method simply loads your discard pile into your hand refreshing your
	 * hand when empty
	 * 
	 * @param player - an object of type Player
	 */
	public static void loadHandFromDiscard(Player player)
	{
		player.setHand(player.getDiscard());
		player.setDiscard(new ArrayList<>());
	}
}
