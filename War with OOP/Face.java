package practice;
public enum Face 
{

	ACE("A", 11),
	TWO("2", 2), 
	THREE("3", 3), 
	FOUR("4", 4), 
	FIVE("5", 5), 
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("Jack", 10),
	QUEEN("Queen", 10),
	KING("King", 10),
	HIDDEN("", 0);
	
	private String number;
	private int numberDigit;
	
	private Face(String number, int numberDigit)
	{
		this.number = number;
		this.numberDigit = numberDigit;
	}

	public String getFaceName()
	{
		return this.number;
	}
	
	public int getFaceValue()
	{
		return this.numberDigit;
	}
	
}
