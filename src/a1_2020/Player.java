package a1_2020;

import java.util.ArrayList;
public class Player {
	private PlayerData data = new PlayerData(0, new ArrayList<Card>());

public Player()
	{                   
	
	}

	public Player(String name){
    this.data.name = name;        
	}
	
	public void setName(String name)
	{
		this.data.name = name;
	}
	
	public String getName()
	{
		return data.name;
	}
	
	public void setScore(int newScore)
	{
		data.score = newScore;
	}
	
	public int getScore()
	{	
		return data.score;
	}
	
	public ArrayList<Card> getHand()
	{
		return data.hand;
	}
	
	public void pickupCard(Card aCard)
	{
		data.hand.add(aCard);
	}
	
	public Card playCard(int cardIndex)
	{
		Card toPlay = data.hand.get(cardIndex);
		data.hand.remove(cardIndex);
		return toPlay;
	}
	
	public void clearHand()
	{
		data.hand.clear();
	}
	
	public int calculateValueOfHand()
	{
		int value = 0;
		
		for (int i = 0; i < data.hand.size(); i++)
		{
			value += data.hand.get(i).getValue();
		}
		
		return value;
	}
	
}
