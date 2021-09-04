/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1_2020;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Balvinder Singh
 */
public class NewCodeImplementTesting {
    
   private static int WIN_SCORE = 300;
	private static int STARTING_CARD_COUNT = 5;
	private Stack<Card> gameDeck= new Stack<Card>();
	private Stack<Card> inPlayDeck = new Stack<Card>();
	
	

        private Player currentPlayer = null;
	

        GameEngine ge = new GameEngine();
   
    String name= "Blue Five";
    String colour="Blue";
    int value=5;
    Card c1=new BlueCard("Blue Five",5);
   
    Card c2=new OrangeCard("Orange Nine",9);
    Card c3=new GreenCard("Green Nine",9);
    Card c4=new PurpleCard("Purple Six",6);
    Card c5=new GreenCard("Green Wham!",10);
	Card c6= new BlueCard("Blue Zero", 0);
	Card c7= new BlueCard ("Blue One", 1);
	Card c8 =new BlueCard ("Blue Two", 2);
	Card c9= new BlueCard ("Blue Three", 3);
	Card c10 =new BlueCard ("Blue Four", 4);
	
    Player py= new Player();
   
    Player py2= new Player();
    Player startingPlayer=null;
    boolean startingPlayerSelected=false;
    boolean roundComplete= false;
    Card tempTopCard = null;
	String chosenCardName = null;
	Card chosenCard = null;
	int playerCardIndex = 0;
    
    public NewCodeImplementTesting() {
    }
    
  
//a       Cards are created and added to the deck appropriately
    @Test
    @SuppressWarnings({ "unchecked", "deprecation" })

    public void cardsCreatedandAdded()  throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        
    	
	     Card input = new BlueCard("Blue Five", 5);
	     

	
	

	//Method method = GameEngine.class.getDeclaredMethod("createCards");
	//method.setAccessible(true);
	     ge.addCardToDeck(input);
	assertEquals(ge.data.gameDeck.peek(),input);
}
      
  
         
   

//b.	Cards in the deck are shuffled before selecting a starting player
    @Test
       public void deckCardsShuffled() 
       {
    	
    	gameDeck.add(c1);
        gameDeck.add(c2);
        gameDeck.add(c4);
        gameDeck.add(c5);
        ge.shuffle();
    
   String firstCard= gameDeck.firstElement().getName();
   System.out.println(firstCard);
ge.shuffle();
   String shuffledFirstCard=gameDeck.firstElement().getName();   
   System.out.println(shuffledFirstCard);
   
     
     	
     	
       
     
    	

}
    
//c.	Players are assigned names and no score to start
    @Test
          public void assignPlayerName()  {
             
    	
         
             String pname="monty";
             Player py= new Player(pname);
             
              py.setName(pname);
              py.setScore(0);
              
              
            
              assertEquals(pname,py.getName());
              assertEquals(py.getScore(),0);
              

}
       
//d.	First player to begin is selected based on the highest number card drawn from the deck
          
          @Test
           public void selectedCurrentPlayerBasedHighestCardValue() {
        	
        	  gameDeck.add(c1);
        	  gameDeck.add(c2);
        	  int p1=gameDeck.pop().getValue();
        	  int p2 = gameDeck.pop().getValue();
        		if (p1 > p2)
    			{
    				startingPlayer = py;
    				  startingPlayerSelected = true;
    			}
    			else if (p2 > p1)
    			{
    				startingPlayer = py2;
    				startingPlayerSelected = true;
    			}
        		
        		 assertEquals(startingPlayer,py);
    		}
        
        	  


//e.	The full deck is shuffled and players are each dealt 5 cards
           @Test
              public void deckShuffledofDealt5Cards() {
        	   py.clearHand();
       		   py2.clearHand();
       		gameDeck.add(c1);
        	gameDeck.add(c2);
        	gameDeck.add(c3);
        	gameDeck.add(c4);
        	gameDeck.add(c5);
        	gameDeck.add(c6);
        	gameDeck.add(c7);
        	gameDeck.add(c8);
        	gameDeck.add(c9);
        	gameDeck.add(c10);


       		for (int i = 0; i < STARTING_CARD_COUNT; i++)
       		{
       			py.pickupCard(gameDeck.pop());
       			py2.pickupCard(gameDeck.pop());
       		}
       		
       		assertEquals(py.getHand().size(),5);
       		assertEquals(py2.getHand().size(),5);

}
//f.	The top card from the deck is placed in play to start the game
            @Test
            public void checkedTopCardPlacedinPlay() {
            	gameDeck.add(c1);
            	gameDeck.add(c2);
            	gameDeck.add(c3);
            	gameDeck.add(c4);
            	gameDeck.add(c5);
            	gameDeck.add(c6);
            	gameDeck.add(c7);
            	gameDeck.add(c8);
            	gameDeck.add(c9);
            	gameDeck.add(c10);
            	inPlayDeck.add(gameDeck.pop());
            	assertNotEquals(gameDeck.peek(),inPlayDeck.peek());
            	
            	
            	

}
//g.	If the top card is a numbered card, the player selects a card to play:
//i.	if a valid card is chosen, this card is removed from the player’s hand and placed in play.
//ii.	if an invalid card is chosen, the card remains in hand and the player selects another card.
//iii.	if no valid card is available in the player’s hand, the player picks up one card from deck.
           @Test
           public void selectCardsPlayGame() {
        	   gameDeck.add(c1);
           	gameDeck.add(c2);
           	gameDeck.add(c3);
           	gameDeck.add(c4);
           	gameDeck.add(c5);
           	gameDeck.add(c6);
           	gameDeck.add(c7);
           	gameDeck.add(c8);
           	gameDeck.add(c9);
           	gameDeck.add(c10);
        		
           
        	   currentPlayer=py;
        	   py.pickupCard(c1);
        	   py.pickupCard(c2);
        	   py.pickupCard(c3);
        	   py.pickupCard(c4);
        	   py.pickupCard(c5);
        	   
        	   inPlayDeck.add(c1);
               inPlayDeck.add(c2);
               inPlayDeck.add(c3);
               inPlayDeck.add(c4);
               inPlayDeck.add(c5);
           
            
				chosenCard = c4;
				chosenCardName = "Purple Six";
                
                String inval_check = null;
               
				
				for (int i = 0; i < currentPlayer.getHand().size(); i++)
				{
					if (currentPlayer.getHand().get(i).getName().equals(chosenCardName))
					{
						chosenCard = currentPlayer.getHand().get(i);
						playerCardIndex = i;
						i = currentPlayer.getHand().size();
					}
				}
				if (chosenCard.getValue() == inPlayDeck.peek().getValue() || 
						chosenCard.getColour() == inPlayDeck.peek().getColour())
				{
					inval_check = "valid";
					
				}
				else
				{
					inval_check ="invalid";
				}
				
				assertEquals(inval_check,"invalid");
				
				chosenCard = c3;
				chosenCardName = "Green Nine";
                
	            String val_check = null;
	               
					
				for (int i = 0; i < currentPlayer.getHand().size(); i++)
				{
					if (currentPlayer.getHand().get(i).getName().equals(chosenCardName))
					{
						chosenCard = currentPlayer.getHand().get(i);
						playerCardIndex = i;
						i = currentPlayer.getHand().size();
					}
				}
				if (chosenCard.getValue() == inPlayDeck.peek().getValue() || 
						chosenCard.getColour() == inPlayDeck.peek().getColour())
				{
					val_check = "valid";
					
				}
				else
				{
					val_check ="invalid";
				}
				
				assertEquals(val_check,"valid");
				
				chosenCard = null;
				chosenCardName = "N";
				int hand_size_before = currentPlayer.getHand().size();
				if(chosenCardName == "N") {
					currentPlayer.pickupCard(gameDeck.pop());
				}
				
				assertNotEquals(currentPlayer.getHand().size(),hand_size_before);
				
                
               
                                
					
        	   
        	   

}
     
            
            
//h.	If the top card is a Wham! card, the player picks up 3 cards.  The top card in the deck is placed in play.
                @Test
           public void topCardWhamCardPlayerPick3cards() {
                	currentPlayer=py;
                	py.pickupCard(c1);
                	py2.pickupCard(c2);
                	gameDeck.add(c1);
                	gameDeck.add(c2);
                	gameDeck.add(c3);
                	gameDeck.add(c4);
                	gameDeck.add(c5);
                inPlayDeck.add(c1);
                inPlayDeck.add(c2);
                inPlayDeck.add(c3);
                inPlayDeck.add(c4);
                inPlayDeck.add(c5);
                
                	if(inPlayDeck.peek().getName().contains("Wham!")) {
                		
                		for (int i = 0; i < 3; i++)
        				{
        					if (gameDeck.size() == 0)
        					{
        						tempTopCard = inPlayDeck.pop();
        						for (int j = 0; j < inPlayDeck.size(); j++)
        						{
        							gameDeck.add(inPlayDeck.pop());
        						}
        						Collections.shuffle(gameDeck);
        					}
        					currentPlayer.pickupCard(gameDeck.pop());
        				}
        				if (gameDeck.size() == 0)
        				{
        					tempTopCard = inPlayDeck.pop();
        					for (int j = 0; j < inPlayDeck.size(); j++)
        					{
        						gameDeck.add(inPlayDeck.pop());
        					}
        					Collections.shuffle(gameDeck);
        				}
        				inPlayDeck.add(gameDeck.pop());
        			}
                		
                	
                	assertEquals(py.getHand().size(),4);
                		
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                	

           
//i.	If all cards in the deck are used, the top card in play remains in place and the rest of the cards are shuffled back into the deck.
          @Test
           public void cardsShuffledallCardsUsed() {
        	  
        	  inPlayDeck.add(c1);
              inPlayDeck.add(c2);
              inPlayDeck.add(c3);
              inPlayDeck.add(c4);
              inPlayDeck.add(c5);
        	  
        		if (gameDeck.size() == 0)
    			{
    				tempTopCard = inPlayDeck.pop();
    				for (int i = 0; i < inPlayDeck.size(); i++)
    				{
    					gameDeck.add(inPlayDeck.pop());
    				}
    				Collections.shuffle(gameDeck);
    			}
        	       assertEquals(tempTopCard,c5);        	  
        	  
        	  
        	  
        	  

} 
//j.	When one player has played all their cards, the round is over and they are the round winner.
          @Test
           public void playerWinner() {
        	 String winner=null;
        	  py.pickupCard(c1);
        	  py2.clearHand();
        	  currentPlayer=py2;
        	  if (currentPlayer.getHand().size() == 0)
  			{
        		  winner=py2.getName() ;

  			}
        	
         	 
         	 assertEquals(winner,py2.getName());

        	  
        	  
        	  
        	  
        	  

} 
//k.	When the round is over, all remaining cards in a player’s hand are scored correctly as per the game rules and this total is added to the winning player’s score.  

        @Test
           public void playerFinalScoredafterroundOver()throws NoSuchMethodException,
           InvocationTargetException, IllegalAccessException {
        	currentPlayer=py2;
        	py.pickupCard(c1);
        	py2.pickupCard(c2);
        	
        	if (currentPlayer.equals(py))
    		{
    			py2.setScore(py2.getScore() + py.calculateValueOfHand());
    		}
    		else if (currentPlayer.equals(py2))
    		{
    			py.setScore(py.getScore() + py2.calculateValueOfHand());
    		}

        	assertEquals(py.getScore(),9);
        	
        	
                            	 
            } 
//l.	When a player reaches 300 points, the game is over and that player wins.

          @Test
           public void checkPlayerPointWinner() {
        	  String winner;
         	 py.setScore(300);
         	 
         	 py2.setScore(200);
         	 py.setName("Balvinder");
         	 py2.setName("harman");

         	 int s1=py.getScore();
         	 int s2=py2.getScore();
         	 if(s1>=WIN_SCORE) {
         		  winner=py.getName();
         	 }
         	 else
         	 {
         		winner=py2.getName() ;
         	 }
         	 
         	 assertEquals(winner,py.getName());

}
          
//check player 2-4 is adding or not
          @Test
           public void checkNoPlayer() {
        	  int noOfPlayer=4;
        	  ge.createPlayers();       	  
        	  assertEquals(ge.data.players.size(),4);
        	  
        	  
        	  
        	  
        	  
        	  
          }
          
          @Test
          public void checkWhamBhamCard() {
        	  Card input= new WhiteActionCard();
       	      	  
       	  assertEquals(input.getName(),"White Wham Bam!");
       	  
       	  
       	  
       	  
       	  
       	  
         }
          
          
}

   
