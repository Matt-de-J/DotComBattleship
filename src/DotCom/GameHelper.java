package DotCom;

import java.io.*;
import java.util.*;
/**
 * This class manages the grid information, creates the random location coordinates, and reads in the user input
 * 
 * Created by HJF
 * @author mattj
 *
 */
public class GameHelper {
	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = 49;
	private int [] grid = new int[gridSize];
	private int comCount = 0;
	
	/**
	 * Accessor for the alphabet instance variable
	 * 
	 * Created by Matt J
	 * @return the alphabet string
	 */
	public static String getAlphabet() {
		return alphabet;
	}
	
	/**
	 * Accessor for the gridlength instance variable
	 * 
	 * Created by Matt J
	 * @return the length of grid, aka num of rows
	 */
	public int getGridLength() {
		return this.gridLength;
	}
	
	/**
	 * Accessor for the grid size
	 * 
	 * Create by Matt J
	 * @return the grid size
	 */
	public int getGridSize() {
		return this.gridSize;
	}
	
	/**
	 * Displays the prompt, aka "Enter a guess: " and returns what the user enters
	 * 
	 * Created by HFJ
	 * @param prompt
	 * @return
	 */
 	public String getUserInput(String prompt) {
		String inputLine = null;
		System.out.print(prompt + " ");		//display the question for the user to answer
		
		//the following will take the input from the user and catch IO exception if invalid
		try {
			BufferedReader is = new BufferedReader(
					new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println("IOEception: " + e);
		}
		
		return inputLine.toLowerCase();		//returns lowercase version of input for standardizations
	}

 	/**
 	 * creates the random coords for the dotcom object
 	 * 
 	 * Created by HFJ
 	 * @param comSize
 	 * @return
 	 */
	public ArrayList<String> placeDotCom(int comSize) {
		ArrayList<String> alphaCells = new ArrayList<String>();
		
		String temp = null;
		int [] coords = new int[comSize];
		int attempts = 0;
		boolean success = false;
		int location = 0;
		
		comCount++;
		int incr = 1;
		if((comCount % 2) == 1) {
			incr = gridLength;
		}
		
		while(!success & attempts++ < 200) {
			location = (int) (Math.random()*gridSize);
			
			int x = 0;
			success = true;
			while(success && x < comSize) {
				if(grid[location] == 0) {
					coords[x++] = location;
					location += incr;
					if(location >= gridSize) {
						success = false;
					}
					if(x > 0 && (location % gridLength == 0)) {
						success = false;
					}
				} else {
					success = false;
				}
			}
		}
		
		int x = 0;
		int row = 0;
		int column = 0;
		
		while (x < comSize) {
			grid[coords[x]] = 1;
			row = (int) (coords[x] / gridLength);
			column = coords[x] % gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
			//System.out.println("Coord " + x + " = " + alphaCells.get(x-1)); //gives away the answers!
		}
		return alphaCells;
	}
}
