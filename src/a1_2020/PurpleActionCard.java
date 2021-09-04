package a1_2020;

public class PurpleActionCard extends PurpleCard implements Actionable{

	

	public PurpleActionCard() {
		super("Purple Wham!", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int cardPenalty() {
		// TODO Auto-generated method stub
		return this.getValue();
	}

}
