package a1_2020;

public class Card {
	
	private String colour;
	private String name;
	private int value;

	
	public Card (String colour)
	{		
		this.colour = colour;
	}
	
	public String getColour()
	{
		return colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString(){
		return this.name;
		
	}
	
	
}
