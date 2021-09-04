package a1_2020;

import java.util.ArrayList;

public class PlayerData {
	public String name;
	public int score;
	public ArrayList<Card> hand;

	public PlayerData(int score, ArrayList<Card> hand) {
		this.score = score;
		this.hand = hand;
	}
}