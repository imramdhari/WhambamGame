package a1_2020;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GameEngineData {
	public Stack<Card> gameDeck;
	public Stack<Card> inPlayDeck;
	public Player currentPlayer;
	public Scanner userInput;
	public ArrayList<Player> players;
	public ArrayList<Integer> scores;

	public GameEngineData(Stack<Card> gameDeck, Stack<Card> inPlayDeck, Player currentPlayer, Scanner userInput,
			ArrayList<Player> players, ArrayList<Integer> scores) {
		this.gameDeck = gameDeck;
		this.inPlayDeck = inPlayDeck;
		this.currentPlayer = currentPlayer;
		this.userInput = userInput;
		this.players = players;
		this.scores = scores;
	}
}