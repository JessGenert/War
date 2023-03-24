package practice;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck
{
	private static int currCard = 0;
	private static Card[] deck;
	private final int NUM_CARDS = 52;
	private Suit[] suits = Suit.values();
	private Face[] faces = Face.values();
	
	public Deck()
	{
		deck = new Card[NUM_CARDS];
		
		for (int i = 0; i < deck.length; i++)
		{
			deck[i] = new Card(suits[i / 13], faces[i % 13]);
		}
		
	}
	
	
	
	public void shuffle()
	{
		currCard = 0;
		
		List<Card> cards = Arrays.asList(deck);
		Collections.shuffle(cards);
		cards.toArray(deck);
		
	}

	public static Card deal()
	{
		Card topCard = deck[currCard];
		currCard++;
		return topCard;
	}

	
	

}
