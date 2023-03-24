package practice;
public enum Suit 
{

	CLUBS("Clubs"),
	DIAMONDS("Diamonds"),
	HEARTS("Hearts"),
	SPADES("Spades"),
	HIDDEN("Hidden Card");

	private String text;
	
	Suit(String text)
	{
		this.text = text;
	}
	
	public String getSuit()
	{
		return text;
	}

}
