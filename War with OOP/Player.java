package WarOOP;

import java.util.ArrayList;

public class Player
{
	
	private ArrayList<Card> hand;
	private ArrayList<Card> discard;
	private boolean hasCardsLeft;
	private String playerName;
	
	
	public Player(String playerName)
	{
		this.hand = new ArrayList<>();
		this.discard = new ArrayList<>();
		this.hasCardsLeft = true;
		this.playerName = playerName;
	}

	public Player()
	{
		
	}


	public void setHand(ArrayList<Card> hand)
	{
		this.hand = hand;
	}
	
	public ArrayList<Card> getHand()
	{
		return this.hand;
	}


	public ArrayList<Card> getDiscard()
	{
		return this.discard;
	}
	
	public void setDiscard(ArrayList<Card> discard)
	{
		this.discard = discard;
	}

	public boolean isCardsLeft()
	{
		return this.hasCardsLeft;
	}

	public void setHasCardsLeft(boolean hasCardsLeft)
	{
		this.hasCardsLeft = hasCardsLeft;
	}
	
	public String getPlayerName()
	{
		return playerName;
	}

}
