package DotCom;

import java.util.*;

public class BoardDisplay {
	

	private GameHelper helper;
	private int cols;
	private int rows;
	private char[][] resultList;
	private String alphabet;
	
	
	
	public void setInitParams(GameHelper h) {
		helper = h;
		alphabet = GameHelper.getAlphabet();
		cols = alphabet.length();
		rows = helper.getGridLength();
		resultList = new char[rows][cols];
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				resultList[r][c] = '\0';
			}
		}
	}

	public void createBoard() {
		//create the column labels
		for(int j = 0; j < cols; j++) {		//GameHelper holds the alphabet for column labels (static var)
			System.out.print("  " + alphabet.charAt(j));
		}
		System.out.println();

		//create row labels and boxes
		for(int x = 0; x < rows; x++) {		//helper variable holds the num of rows (non-static var)
			System.out.print(x+1);
			for(int y = 0; y < cols; y++) {
				System.out.print("[ ]");
			}
			System.out.println();
		}
		
	}
	
	public void addResult(String result, String guess) {
		int guessNum = guess.charAt(guess.length() - 1) - 49;
		char guessAlpha = guess.charAt(0);
		if(guessNum >= 0 && guessNum <= rows) {
			if(result == "miss") {
				for(int a = 0; a < cols; a++) {
					if(guessAlpha == alphabet.charAt(a)) {
						resultList[guessNum][a] = 'O';
					}
				}
			}
			else if(result == "hit" || result == "kill") {
				for(int a = 0; a < cols; a++) {
					if(guessAlpha == alphabet.charAt(a)) {
						resultList[guessNum][a] = 'X';
					}
				}
			}
		}
		
	}
	
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
					System.out.print("[" + resultList[x][y] + "]");
				}
				else {
					System.out.print("[ ]");	
				}
			}
			System.out.println();
		}
		
		
	}
}
