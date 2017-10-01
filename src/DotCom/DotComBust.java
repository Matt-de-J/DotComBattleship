package DotCom;

import java.util.*;

/**
 * 
 * @author mattj
 * @created 10/1/2017
 * 
 * This program idea and core code was provided by the text "Head First Java 2nd Edition" written by Kathy Sierra & Bert Bates
 * 
 * Description: This package will run a battleship-styled game in the terminal. Three "battleships"
 * in the form a website names will be created. They will be of length 3 hits. A user will then be allowed to guess a coordinate
 * on the displayed grid, and if the guess is a hit then an X will be displayed at that position. If it's a miss, a O is displayed.
 * Once all three coordinates of a dot-com is hit, that will be considered a "kill" aka sunk. After all three dot-coms are sunk, the game
 * will end and display the number of guesses taken.
 * 
 * The code "given" through the textbook is described within the class at the spot of method definition.
 */

public class DotComBust {
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	BoardDisplay board = new BoardDisplay();
	
	private void setUpGame() {
		DotCom one = new DotCom();
		one.setName("Pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("Go2.com");
		
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		System.out.println("Your goal is to sink three dot coms.");
		System.out.println(one.getName() + " " + two.getName() + " " + three.getName());
		System.out.println("To Guess: Enter the column letter, followed by the row number (i.e. a1), then press enter.");
		System.out.println("Try to sink em all in the fewest number of guesses. Good Luck!");
		
		for(DotCom dotComToSet : dotComsList) {
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}
		board.setInitParams(helper);
		board.updateBoard();
	}
	
	private void startPlaying() {
		while(!dotComsList.isEmpty()) {
			String userGuess = helper.getUserInput("Enter a guess: ");
			checkUserGuess(userGuess);
		}
		finishGame();
	}
	
	private void checkUserGuess(String userGuess) {
		numOfGuesses++;
		String result = "miss";
		
		for(int x = 0; x < dotComsList.size(); x++) {
			result = dotComsList.get(x).checkYourself(userGuess);
			
			if(result.equals("hit")) {
				break;
			}
			if(result.equals("kill")) {
				dotComsList.remove(x);
				break;
			}
		}
		board.addResult(result, userGuess);
		board.updateBoard();
		System.out.println(result);
	}
	
	private void finishGame() {
		System.out.println("All Dot Coms are dead! Your stock is now worthless...");
		if(numOfGuesses <= 18) {
			System.out.println("Nice, it only took you " + numOfGuesses + " guesses.");
		}
		else {
			System.out.println("Hmmm... took you long enough... " + numOfGuesses + " guesses.");
		}
	}
	
	public static void main(String[] args) {
		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlaying();
	}
}
