package DotCom;

import java.util.*;

/**
 * This class handles the board that displays the coordinates, and the result of a users guess.
 * If the guess is a hit, the display will show an X in the relevant coordinate. If the guess is
 * a miss, it displays an O
 * 
 * Created by Matt J
 * @author mattj
 *
 */
public class BoardDisplay {
	
	//instance variables
	private GameHelper helper; //used to access game parameters needed to set grid size
	private int cols;
	private int rows;
	private char[][] resultList; //holds the guesses made by the user
	private String alphabet;
	
	
	/**
	 * Takes the gamehelper instance variables to setup basic initializing info for the board
	 * 
	 * Created by Matt J
	 * @param h
	 */
	public void setInitParams(GameHelper h) {
		helper = h;
		alphabet = GameHelper.getAlphabet();		//the letters used for column labels
		cols = alphabet.length();		//the number of letters, aka num of columns
		rows = helper.getGridLength();		//grid length is the number of rows
		resultList = new char[rows][cols];		//initialize the result array as the size of the grid
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				resultList[r][c] = '\0';		//sets up the result list as null characters
			}
		}
	}

	/**
	 * Takes the guess parameter and the result of that guess (hit/miss/kill) and adds it to the array
	 * of results
	 * 
	 * Created by Matt J
	 * @param result
	 * @param guess
	 */
	public void addResult(String result, String guess) {
		int guessNum = guess.charAt(guess.length() - 1) - 49;		//assigns the final character of the guess string as the number that the user guessed
		char guessAlpha = guess.charAt(0);		//takes the first char of the guess and assigns it as the alpha guess. This means that the grid can NOT be multi-character
		if(guessNum >= 0 && guessNum <= rows) {		//ensures the guess number entered is valid
			if(result == "miss") {
				for(int a = 0; a < cols; a++) {		//sifts through all the columns
					if(guessAlpha == alphabet.charAt(a)) {		//compares the column alpha to the usersguess alpha
						resultList[guessNum][a] = 'O';		//if they are equal, then the guess was within the grid parameters and is assigned an O
					}
				}
			}
			else if(result == "hit" || result == "kill") {		//in the event that, for some reason, the check returns something other than hit/kill/miss, this filters that out
				for(int a = 0; a < cols; a++) {		//sifts through all the columns
					if(guessAlpha == alphabet.charAt(a)) {		//compares the column alpha to the usersguess alpha
						resultList[guessNum][a] = 'X';		//if they are equal, then the guess was within the grid parameters and is assigned an X
					}
				}
			}
		}
		
	}

	/**
	 * Prints the board to the terminal with the users guess
	 * 
	 * Created by Matt J
	 */
	public void updateBoard() {
		//create the column labels
		for(int j = 0; j < cols; j++) {		//GameHelper holds the alphabet for column labels (static var)
			System.out.print("  " + alphabet.charAt(j));
		}
		System.out.println();
		
		//create row labels and boxes
		for(int x = 0; x < rows; x++) {		//helper variable holds the num of rows (non-static var)
			System.out.print(x+1);
			for(int y = 0; y < cols; y++) {
				if(resultList[x][y] != '\0') {
					System.out.print("[" + resultList[x][y] + "]");		//prints the hit/miss character
				}
				else {
					System.out.print("[ ]");	
				}
			}
			System.out.println();
		}
		
		
	}
}
