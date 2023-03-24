package practice;
public class Card 
{

	private Face face;
	private Suit suit;
	
	public Card(Suit suit, Face face)
	{
		this.face = face;
		this.suit = suit;
	}
	
	public String getFaceString() 
	{
		
		return face.getFaceName();
	}
	
	public int getFaceDigit() 
	{
		
		return face.getFaceValue();
	}

	public String getSuit() 
	{
		return suit.getSuit();
	}
	
	
	@Override
	public String toString()
	{
		return  getFaceString() + " of " + getSuit();
	}

}
