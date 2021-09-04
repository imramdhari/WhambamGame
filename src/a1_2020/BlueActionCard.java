package a1_2020;

public class BlueActionCard extends BlueCard implements Actionable{

	

	public BlueActionCard() {
		super("Blue Wham!", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int cardPenalty() {
		 return super.getValue();
		// TODO Auto-generated method stub
		
	}

}
