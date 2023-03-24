package WarOOP;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
	private ArrayList<Card> pileOfCards = new ArrayList<>();
	
	/**
	 * Initializing the deck with the suits and faces of each card
	 */
	public void initializeDeck()
	{
		for(Suit suit : Suit.values())
		{
			for(Face face : Face.values())
			{
				pileOfCards.add(new Card(face, suit));
			}
		}
	}
	
	/**
	 * A method for shuffling the deck after we initialize it
	 */
	public void shuffle()
	{
		Collections.shuffle(pileOfCards);
	}
	
	/**
	 * A method for transforming a deck into an array list of cards in order to be able to iterate through it
	 * @return -  ArrayList of type Card
	 */
	public ArrayList<Card> toCardsList()
	{
		return pileOfCards;
	}
	
}
