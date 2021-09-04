package a1_2020;

public class OrangeActionCard extends OrangeCard implements Actionable{

	public OrangeActionCard( ) {
		super( "Orange Wham!", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int cardPenalty() {
		// TODO Auto-generated method stub
		return super.getValue();
	}

}
