package practice;
import java.util.Scanner;

public class Game
{

	public static void main(String[] args)
	{

	    Scanner scanner = new Scanner(System.in);
	    boolean playerTurn = true;
	    Card hiddenCard;
	    final int STAND = 17;
	    boolean stillPlaying = true;

	    System.out.println("What nickName would you like to have?");
	    String name = scanner.nextLine();
	    

	    System.out.println("Would you like to start a new game?  'Yes/No' :");
	    String answer = scanner.nextLine();
	    
	    
	    while(stillPlaying)
	    {
	    	
		
		    
	    
	    while(answer.equalsIgnoreCase("Yes"))
	    {
	    	

	    	Player player = new Player(name);
		    Player dealer = new Player("Dealer");
		    
		    
	    	System.out.println("Please Place your bet: 10, 20, or 30");
		    double bet = Double.parseDouble(scanner.nextLine());
	    	player.setBet(bet);
	    	Deck deck = new Deck();
	    	deck.shuffle();
	    	
	    	player.setHand(player.getHand());
	    	dealer.setHand(dealer.getHand());
	    	
	    	hiddenCard = dealer.getHand().get(0);
	    	dealer.getHand().remove(0);
	    	Card card = new Card(Suit.HIDDEN, Face.HIDDEN);
	    	dealer.getHand().add(0, card);
	    	System.out.println(dealer);
	    	
	    	while(playerTurn)
	    	{
	    		
	    		System.out.println(player);
	    		System.out.println("Would you like to hit, stand, or double down? - Hit/Stand/DD");
	    		String nextMove = scanner.nextLine();
	    		if(nextMove.equalsIgnoreCase("Hit"))
	    		{
	    			player.hit(player.getHand());
	    		}
	    		else if(nextMove.equalsIgnoreCase("DD") && player.getHand().size() == 2)
	    		{
	    			player.hit(player.getHand());
	    			player.setBet(bet*2);
	    			System.out.println(player);
	    			playerTurn = false;
	    		}
	    		else if(nextMove.equalsIgnoreCase("Stand"))
	    		{
	    			playerTurn = false;
	    		}
	    		else
	    		{
	    			System.out.println("That is not a valid input.");
	    		}
	    		
	    		if(player.getValue(player.getHand()) > 21)
	    		{
	    			System.out.println(player);
	    			System.out.println("You Busted, you lose");
		    		System.out.println("Would you like to play again? - Yes/No");
		    		answer = scanner.nextLine();
		    		if(answer.equalsIgnoreCase("No"))
		    		{
		    		  stillPlaying = false;
		    		}
	    			
	    		}
		    	
	    	}
	    	
	    	dealer.getHand().remove(0);
	    	dealer.getHand().add(hiddenCard);
	    	
	    	System.out.println(dealer);
	    	
	    	while(dealer.getValue(dealer.getHand()) < STAND)
	    	{
	    		dealer.hit(dealer.getHand());
	    		System.out.println(dealer);
	    		
	    		if(dealer.getValue(dealer.getHand()) > 21)
	    		{
	    			
		    		break;
	    			
	    		}
	    	}
	    	
	    	if(dealer.getValue(dealer.getHand()) > 21)
    		{
    			System.out.printf("Dealer bust! you win! %.1f money\n", player.getBet());
    			System.out.println("Would you like to play again? - Yes/No");
    			answer = scanner.nextLine();
	    		if(answer.equalsIgnoreCase("No"))
	    		{
	    		  stillPlaying = false;
	    		}
	    			playerTurn = true;
	    		
    			
    		}
	    	
	    	else if(player.getValue(player.getHand()) > dealer.getValue(dealer.getHand()))
	    	{
	    		if(player.getValue(player.getHand()) == 21 && player.getHand().size() == 2)
	    		{
	    			player.setBet(bet*1.5);
	    		}
	    		System.out.printf("You win %.1f money with a score of %d and a dealer score of %d \n", player.getBet(), player.getValue(player.getHand()), dealer.getValue(dealer.getHand()) );
	    		System.out.println("Would you like to play again? - Yes/No");
	    		answer = scanner.nextLine();
	    		if(answer.equalsIgnoreCase("No"))
	    		{
	    		  stillPlaying = false;
	    		}
	    		playerTurn = true;
	    	}
	    	else if(player.getValue(player.getHand()) == dealer.getValue(dealer.getHand()))
	    	{
	    		System.out.println("Push! you get your money back");
	    		System.out.println("Would you like to play again? - Yes/No");
	    		answer = scanner.nextLine();
	    		if(answer.equalsIgnoreCase("No"))
	    		{
	    		  stillPlaying = false;
	    		}
	    		playerTurn = true;
	    	}
	    	else
	    	{
	    		System.out.printf("Dealer wins, you lost %.1f money\n", player.getBet());
	    		System.out.println("Would you like to play again? - Yes/No");
	    		answer = scanner.nextLine();
	    		if(answer.equalsIgnoreCase("No"))
	    		{
	    		  stillPlaying = false;
	    		}
	    		playerTurn = true;
	    	}
	    	
	    	
	    	
	    }
	   
	    }
	    scanner.close();
	    System.out.println("Thank you for playing BlackJack!");
	    System.exit(1);
	}
	

}
