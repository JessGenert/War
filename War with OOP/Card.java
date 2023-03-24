package WarOOP;

public class Card
{
	
	private Face face;
	private Suit suit;
	
	
	public Card(Face face, Suit suit)
	{
		this.suit = suit;
		this.face = face;
	}


	public Face getFace()
	{
		return this.face;
	}
	
	public int getFaceValue()
	{
		return face.getFaceValue();
	}

	public Suit getSuit()
	{
		return this.suit;
	}
	
	@Override
	public String toString()
	{
		return getFace() + " of " + getSuit();
	}

}
