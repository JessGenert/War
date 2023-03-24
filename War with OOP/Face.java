package WarOOP;

public enum Face
{
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);

	// The code blocks below allow you to access the int value of the card.
	private int faceValue;
	
	
	private Face(int faceValue)
	{
		this.faceValue = faceValue;
	}
	
	
	public int getFaceValue()
	{
		return this.faceValue;
	}
	
}
