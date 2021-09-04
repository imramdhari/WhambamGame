package a1_2020;

public class WhiteActionCard extends WhiteCard implements Actionable{

	

	public WhiteActionCard() {
		super("White Wham Bam!", 15);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int cardPenalty() {
		// TODO Auto-generated method stub
		return this.getValue();
	}

}

