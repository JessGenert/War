package practice;
import java.util.ArrayList;

public class Player
{
	private String name;
	private double bet;
	private ArrayList<Card> hand;


	public Player(String name)
	{
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.bet = 0;
	}
	



	public double getBet()
	{
		return bet;
	}


	public void setBet(double bet)
	{
		this.bet = bet;
	}


	public ArrayList<Card> getHand()
	{
		return hand;
	}


	public void setHand(ArrayList<Card> hand)
	{
		hand.add(Deck.deal());
		hand.add(Deck.deal());
		this.hand = hand;
	}
	
	
	public int getValue(ArrayList<Card> hand)
	{
		int value = 0;
		int numberOfAces = 0;
		for (Card card : hand)
		{
			if(card.getFaceString() == "A")
			{
				numberOfAces++;
			}
			value += card.getFaceDigit();
			if(numberOfAces >= 1 && value > 21)
			{
				value -= 10;
			}
			
		}
		return value;
	}
	
	public void hit(ArrayList<Card> hand)
	{
		hand.add(Deck.deal());
		this.hand = hand;
	}
	
	@Override
	public String toString()
	{
		
		return this.name + "'s current hand is:" + this.hand.toString() + "\nWith a value of: " + getValue(this.hand) ;
	}

}
