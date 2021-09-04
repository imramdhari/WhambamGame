package a1_2020;

public class GreenActionCard extends GreenCard implements Actionable{

	public GreenActionCard( ) {
		super("Green Wham!", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int cardPenalty() {
		// TODO Auto-generated method stub
		return super.getValue();
	}

}
