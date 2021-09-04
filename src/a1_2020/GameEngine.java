package a1_2020;

import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
//ploi
public class GameEngine {
	public static int WIN_SCORE = 300;
	public static int STARTING_CARD_COUNT = 5;
	public GameEngineData data = new GameEngineData(new Stack<Card>(), new Stack<Card>(), null, new Scanner(System.in), new ArrayList<Player>(),
			new ArrayList<>());

	public GameEngine(){}
	
	private boolean checkScore() {
		for(Player p:data.players) {
			if(p.getScore()>WIN_SCORE) {
				return false;
			}
			
		}
		return true;
	}
	
	
	public void play()
	{
		System.out.println("Welcome to Wham Bam!");
		createPlayers();
		do {
			System.out.println("* Starting new round *");
			newRound();
			System.out.println("* Round OVER *");
			scoreGame();
			}while (checkScore()==true);	
	}
	
	


	public void newRound()
	{
		Card tempTopCard = null;
		String chosenCardName = null;
		Card chosenCard = null;
		int playerCardIndex = 0;

		boolean roundComplete = false;
		boolean validChoice = false;
		boolean errorOccurred = false;

		createCards();
		shuffle();
		
		data.currentPlayer = selectStartingPlayer();
		data.gameDeck.clear();
		createCards();
		shuffle();
		deal();
		data.inPlayDeck.add(data.gameDeck.pop());

		while (!roundComplete)
		{	
			// if the deck is empty, replenish it
			if (data.gameDeck.size() == 0)
			{
				tempTopCard = data.inPlayDeck.pop();
				for (int i = 0; i < data.inPlayDeck.size(); i++)
				{
					data.gameDeck.add(data.inPlayDeck.pop());
				}
				shuffle();
			}

			// show the top card and current player
			System.out.println("Top Card in Play: " + data.inPlayDeck.peek());
			System.out.println("Current Player: " + data.currentPlayer.getName());

			// handle Wham! card first if needed
			if (data.inPlayDeck.peek().getName().contains("Wham!"))
			{
				System.out.println("Wham Card Active!  Penalty: pick up 3 cards");
				// Make sure we can pick up cards
				for (int i = 0; i < 3; i++)
				{
					if (data.gameDeck.size() == 0)
					{
						tempTopCard = data.inPlayDeck.pop();
						for (int j = 0; j < data.inPlayDeck.size(); j++)
						{
							data.gameDeck.add(data.inPlayDeck.pop());
						}
						shuffle();
					}
					data.currentPlayer.pickupCard(data.gameDeck.pop());
				}
				if (data.gameDeck.size() == 0)
				{
					tempTopCard = data.inPlayDeck.pop();
					for (int j = 0; j < data.inPlayDeck.size(); j++)
					{
						data.gameDeck.add(data.inPlayDeck.pop());
					}
					shuffle();
				}
				data.inPlayDeck.add(data.gameDeck.pop());
			}
			//Handle Wham Bam! card
			else if (data.inPlayDeck.peek().getName().contains("Bam!"))
			{
				
				if (data.gameDeck.size() == 0)
				{
					tempTopCard = data.inPlayDeck.pop();
					for (int j = 0; j < data.inPlayDeck.size(); j++)
					{
						data.gameDeck.add(data.inPlayDeck.pop());
					}
					shuffle();
				}

				// pick up card and end turn.
				data.currentPlayer.pickupCard(data.gameDeck.pop());
				
				if(tempTopCard.getName().contains("Bam!")) {
					System.out.println("Wham Bam Card Active!  Penalty: pick up 9 cards");
					// Make sure we can pick up cards
					for (int i = 0; i < 9; i++)
					{
						if (data.gameDeck.size() == 0)
						{
							tempTopCard = data.inPlayDeck.pop();
							for (int j = 0; j < data.inPlayDeck.size(); j++)
							{
								data.gameDeck.add(data.inPlayDeck.pop());
							}
							shuffle();
						}
						data.currentPlayer.pickupCard(data.gameDeck.pop());
						if (data.gameDeck.size() == 0)
						{
							tempTopCard = data.inPlayDeck.pop();
							for (int j = 0; j < data.inPlayDeck.size(); j++)
							{
								data.gameDeck.add(data.inPlayDeck.pop());
							}
							shuffle();
						}
						data.inPlayDeck.add(data.gameDeck.pop());
					}		
				}
				else if(tempTopCard.getName().contains("Wham!")) {
					System.out.println("Wham Card Active!  Penalty: pick up 3 cards");
					// Make sure we can pick up cards
					for (int i = 0; i < 3; i++)
					{
						if (data.gameDeck.size() == 0)
						{
							tempTopCard = data.inPlayDeck.pop();
							for (int j = 0; j < data.inPlayDeck.size(); j++)
							{
								data.gameDeck.add(data.inPlayDeck.pop());
							}
							shuffle();
						}
						data.currentPlayer.pickupCard(data.gameDeck.pop());
					}
					if (data.gameDeck.size() == 0)
					{
						tempTopCard = data.inPlayDeck.pop();
						for (int j = 0; j < data.inPlayDeck.size(); j++)
						{
							data.gameDeck.add(data.inPlayDeck.pop());
						}
						shuffle();
					}
					data.inPlayDeck.add(data.gameDeck.pop());
					roundComplete = true;
				}
				
				
			}
			else
			{
				// General number card on top.
				validChoice = false;
				errorOccurred =  false;
				chosenCard = null;

				while (!validChoice)
				{
					System.out.println("Cards in hand: ");
					System.out.println(data.currentPlayer.getHand().toString());
					System.out.println("Select a card to play (N to pick up from deck): ");

					chosenCardName = data.userInput.nextLine();

					if (chosenCardName.equals("N"))
					{
						// choosing to pick up.  Needs to not have a valid card to play in hand.
						for (int i = 0; i < data.currentPlayer.getHand().size(); i++)
						{
							if (data.currentPlayer.getHand().get(i).getValue() == (data.inPlayDeck.peek().getValue()) || data.currentPlayer.getHand().get(i).getColour().equals(data.inPlayDeck.peek().getColour())) 
							{
								System.out.println("Unable to pickup card: you have at least one valid card to play in your hand.");
								i = data.currentPlayer.getHand().size();
								errorOccurred = true;
							}
						}
						if (!errorOccurred)
						{
							// make sure there are cards to pick up.
							if (data.gameDeck.size() == 0)
							{
								tempTopCard = data.inPlayDeck.pop();
								for (int j = 0; j < data.inPlayDeck.size(); j++)
								{
									data.gameDeck.add(data.inPlayDeck.pop());
								}
								shuffle();
							}

							// pick up card and end turn.
							data.currentPlayer.pickupCard(data.gameDeck.pop());

							validChoice = true;
						}
					}
					
					else
					{
						// player has selected a card.  Search hand for their selection.
						for (int i = 0; i < data.currentPlayer.getHand().size(); i++)
						{
							if (data.currentPlayer.getHand().get(i).getName().equals(chosenCardName))
							{
								chosenCard = data.currentPlayer.getHand().get(i);
								playerCardIndex = i;
								i = data.currentPlayer.getHand().size();
							}
						}
						if (chosenCard == null)
						{
							System.out.println("Unable to locate card.  Please make sure you match the card name listed");
						}
						else
						{
							// card has been found in the player's hand.
							if (chosenCard.getValue() == data.inPlayDeck.peek().getValue() || chosenCard.getColour() == data.inPlayDeck.peek().getColour())
							{
								//	valid choice to play card
								data.inPlayDeck.add(data.currentPlayer.playCard(playerCardIndex));
								validChoice = true;
							}
							else
							{
								System.out.println("Invalid card selection.  Must match colour or number of top card in deck.  Please retry.");
							}
						}
					}
				}
			}
			// see if player has emptied hand - if so, the round is over
			if (data.currentPlayer.getHand().size() == 0)
			{
				roundComplete = true;
			}
			//next player's turn
			data.currentPlayer = data.players.get((data.players.indexOf(data.currentPlayer)+1)%data.players.size());			
		}		
	}	

	public void shuffle()
	{
		Collections.shuffle(data.gameDeck);
	}

	public void deal()
	{
		
		for(Player p:data.players){
			p.clearHand();
			for (int i = 0; i < STARTING_CARD_COUNT; i++)
			{
				p.pickupCard(data.gameDeck.pop());				
			}			
		}	
	}
	
	public Player selectStartingPlayer()
	{
		boolean startingPlayerSelected = false;
		
		Player startingPlayer = null;
		ArrayList<Card> cards = new ArrayList<>();
		while (!startingPlayerSelected)
		{
			for(int i=0;i<data.players.size();i++) {
				cards.add(data.gameDeck.pop());
			}
			
						 
			int c_val = 0;			 		     
		    for (int i = 0; i < cards.size() ; i++) {
		           if (cards.get(i).getValue() > c_val) 
		                { 
		                	c_val = cards.get(i).getValue();
		                	startingPlayer = data.players.get(i);
		                } 
		    }
	        startingPlayerSelected = true;		     
		}
		return startingPlayer;
	}

	public void scoreGame()
	{		
		int wins = 0;
		int handValue = data.currentPlayer.calculateValueOfHand();
		for(int i=0;i<data.players.size();i++) {
			if(data.players.get(i).equals(data.currentPlayer)) {
				
				continue;
			}
			wins += data.players.get(i).getScore();
		}		
		for(Player p:data.players) {
			if(p.getHand().size()==0) {
				p.setScore(wins+handValue);
			}
		}
	 
	     System.out.println("Scores at the end of round:");
	     for(Player p:data.players) {
	    	 System.out.println(p.getName()+":"+p.getScore());
	     }    
	}
	
	public void createCards()
	{
		
		String[] numbers= {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
		
		
		
		
		//zero cards
		
			addCardToDeck(new BlueCard("Blue Zero",0));
			addCardToDeck(new GreenCard("Green Zero",0));
			addCardToDeck(new PurpleCard("Purple Zero",0));
			addCardToDeck(new OrangeCard("Orange Zero",0));
		
		
		//wham cards
		for(int p=0;p<2;p++) {
			addCardToDeck(new BlueActionCard());
			addCardToDeck(new PurpleActionCard());
			addCardToDeck(new OrangeActionCard());
			addCardToDeck(new GreenActionCard());
			
		}
		
		//wham bam cards
		int WB_COUNT = 4;
		for(int i=0;i<WB_COUNT;i++) {
			addCardToDeck(new WhiteActionCard());
		}
		
		for(int i=0;i<numbers.length;i++) {
			addCardToDeck(new BlueCard("Blue "+numbers[i],i));
			addCardToDeck(new BlueCard("Blue "+numbers[i],i));
			addCardToDeck(new GreenCard("Green "+numbers[i],i));
			addCardToDeck(new GreenCard("Green "+numbers[i],i));
			addCardToDeck(new PurpleCard("Purple "+numbers[i],i));
			addCardToDeck(new PurpleCard("Purple "+numbers[i],i));
			addCardToDeck(new OrangeCard("Orange "+numbers[i],i));
			addCardToDeck(new OrangeCard("Orange "+numbers[i],i));
		}
		
	}

	public void addCardToDeck(Card theCard)
	{
		data.gameDeck.add(theCard);
	}

	public void createPlayers()
	{
		System.out.println("Enter number of players:");
		int i = Integer.parseInt(data.userInput.nextLine());
		
		while (i<2 || i>4) {
			System.out.println("Number of players must be between 2 and 4");
			System.out.println("Enter number of players:");
			 i = Integer.parseInt(data.userInput.nextLine());
		}
		
		for(int j=0;j<i;j++) {
			System.out.println("Enter Player Name:");
			String input = data.userInput.nextLine();
			data.players.add(new Player(input));
		}
		
	}
	
}